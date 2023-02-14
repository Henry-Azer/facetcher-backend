import jittor as jt
from jittor import nn


###############################################################################
# Functions
###############################################################################

def weights_init_normal(m):
    classname = m.__class__.__name__
    if classname.find("Conv") != -1:
        jt.init.gauss_(m.weight, 0.0, 0.02)
    elif classname.find("BatchNorm") != -1:
        jt.init.gauss_(m.weight, 1.0, 0.02)
        jt.init.constant_(m.bias, 0.0)


def get_norm_layer(norm_type='instance'):
    if (norm_type == 'batch'):
        norm_layer = nn.BatchNorm
    elif (norm_type == 'instance'):
        norm_layer = nn.InstanceNorm2d
    else:
        raise NotImplementedError(('normalization layer [%s] is not found' % norm_type))

    return norm_layer


class MSELoss:
    def __init__(self):
        pass

    def __call__(self, output, target):
        from jittor.nn import mse_loss

        return mse_loss(output, target)


class BCELoss:
    def __init__(self):
        pass

    def __call__(self, output, target):
        from jittor.nn import bce_loss

        return bce_loss(output, target)


############################
# Model function
############################

def define_part_encoder(model='mouth', norm='instance', input_nc=1, latent_dim=512):
    norm_layer = get_norm_layer(norm_type=norm)
    image_size = 512
    if 'eye' in model:
        image_size = 128
    elif 'mouth' in model:
        image_size = 192
    elif 'nose' in model:
        image_size = 160
    elif 'face' in model:
        image_size = 512
    else:
        print("Whole Image !!")

    # input longsize 256 to 512*4*4
    net_encoder = EncoderGenerator_Res(norm_layer, image_size, input_nc, latent_dim)
    print("net_encoder of part " + model + " is:", image_size)

    return net_encoder


def define_part_decoder(model='mouth', norm='instance', output_nc=1, latent_dim=512):
    norm_layer = get_norm_layer(norm_type=norm)

    image_size = 512
    if 'eye' in model:
        image_size = 128
    elif 'mouth' in model:
        image_size = 192
    elif 'nose' in model:
        image_size = 160
    else:
        print("Whole Image !!")

    # input longsize 256 to 512*4*4
    net_decoder = DecoderGenerator_image_Res(norm_layer, image_size, output_nc, latent_dim)
    print("net_decoder to image of part " + model + " is:", image_size)

    return net_decoder


def define_feature_decoder(model='mouth', norm='instance', output_nc=1, latent_dim=512):
    norm_layer = get_norm_layer(norm_type=norm)

    image_size = 512
    if 'eye' in model:
        image_size = 128
    elif 'mouth' in model:
        image_size = 192
    elif 'nose' in model:
        image_size = 160
    else:
        print("Whole Image !!")

    # input longsize 256 to 512*4*4
    net_decoder = DecoderGenerator_feature_Res(norm_layer, image_size, output_nc, latent_dim)
    print("net_decoder to image of part " + model + " is:", image_size)

    return net_decoder


def define_G(input_nc, output_nc, ngf, n_downsample_global=3, n_blocks_global=9, norm='instance'):
    norm_layer = get_norm_layer(norm_type=norm)
    netG = GlobalGenerator(input_nc, output_nc, ngf, n_downsample_global, n_blocks_global, norm_layer)

    return netG


##############################################################################
# Losses
##############################################################################


# define a resnet block
class ResnetBlock(nn.Module):
    def __init__(self, dim, padding_type, norm_layer, activation=nn.ReLU(True), use_dropout=False):
        super(ResnetBlock, self).__init__()
        self.conv_block = self.build_conv_block(dim, padding_type, norm_layer, activation, use_dropout)

    def build_conv_block(self, dim, padding_type, norm_layer, activation, use_dropout):
        conv_block = []
        p = 0
        if (padding_type == 'reflect'):
            conv_block += [nn.ReflectionPad2d(1)]
        elif (padding_type == 'replicate'):
            conv_block += [nn.ReplicationPad2d(1)]
        elif (padding_type == 'zero'):
            p = 1
        else:
            raise NotImplementedError(('padding [%s] is not implemented' % padding_type))
        conv_block += [nn.Conv(dim, dim, 3, padding=p), norm_layer(dim), activation]
        if use_dropout: conv_block += [nn.Dropout(0.5)]

        p = 0
        if (padding_type == 'reflect'):
            conv_block += [nn.ReflectionPad2d(1)]
        elif (padding_type == 'replicate'):
            conv_block += [nn.ReplicationPad2d(1)]
        elif (padding_type == 'zero'):
            p = 1
        else:
            raise NotImplementedError(('padding [%s] is not implemented' % padding_type))
        conv_block += [nn.Conv(dim, dim, 3, padding=p), norm_layer(dim)]

        return nn.Sequential(*conv_block)

    def execute(self, x):
        out = (x + self.conv_block(x))

        return out


class EncoderGenerator_Res(nn.Module):
    def __init__(self, norm_layer, image_size, input_nc, latent_dim=512):
        super(EncoderGenerator_Res, self).__init__()
        layers_list = []

        latent_size = int(image_size / 32)
        longsize = 512 * latent_size * latent_size
        self.longsize = longsize

        activation = nn.ReLU()
        padding_type = 'reflect'
        norm_layer = nn.BatchNorm

        # encode
        # 176 176
        layers_list.append(EncoderBlock(channel_in=input_nc, channel_out=32, kernel_size=4, padding=1, stride=2))

        dim_size = 32
        for i in range(4):
            layers_list.append(ResnetBlock(dim_size, padding_type=padding_type, activation=activation, norm_layer=norm_layer))
            layers_list.append(EncoderBlock(channel_in=dim_size, channel_out=dim_size * 2, kernel_size=4, padding=1, stride=2))
            dim_size *= 2

        layers_list.append(ResnetBlock(512, padding_type=padding_type, activation=activation, norm_layer=norm_layer))

        # final shape Bx256*7*6
        self.conv = nn.Sequential(*layers_list)
        self.fc_mu = nn.Sequential(nn.Linear(in_features=longsize, out_features=latent_dim))  # ,

        for m in self.modules(): weights_init_normal(m)

    def execute(self, ten):
        ten = self.conv(ten)
        ten = jt.reshape(ten, [ten.size()[0], -1])
        mu = self.fc_mu(ten)

        return mu


class DecoderGenerator_image_Res(nn.Module):
    def __init__(self, norm_layer, image_size, output_nc, latent_dim=512):
        super(DecoderGenerator_image_Res, self).__init__()
        # start from B*1024
        latent_size = int(image_size / 32)
        self.latent_size = latent_size
        longsize = 512 * latent_size * latent_size

        activation = nn.ReLU()
        padding_type = 'reflect'
        norm_layer = nn.BatchNorm

        self.fc = nn.Sequential(nn.Linear(in_features=latent_dim, out_features=longsize))

        # 176 176
        layers_list = []
        layers_list.append(ResnetBlock(512, padding_type=padding_type, activation=activation, norm_layer=norm_layer))

        dim_size = 256
        for i in range(4):
            # latent*2
            layers_list.append(DecoderBlock(channel_in=dim_size * 2, channel_out=dim_size, kernel_size=4, padding=1, stride=2, output_padding=0))
            layers_list.append(ResnetBlock(dim_size, padding_type=padding_type, activation=activation, norm_layer=norm_layer))
            dim_size = int(dim_size / 2)

        # 352 352
        layers_list.append(DecoderBlock(channel_in=32, channel_out=32, kernel_size=4, padding=1, stride=2, output_padding=0))
        layers_list.append(ResnetBlock(32, padding_type=padding_type, activation=activation, norm_layer=norm_layer))  # 176 176

        layers_list.append(nn.ReflectionPad2d(2))
        layers_list.append(nn.Conv(32, output_nc, kernel_size=5, padding=0))

        self.conv = nn.Sequential(*layers_list)

        for m in self.modules():
            weights_init_normal(m)

    def execute(self, ten):
        ten = self.fc(ten)
        ten = jt.reshape(ten, (ten.size()[0], 512, self.latent_size, self.latent_size))
        ten = self.conv(ten)

        return ten


class DecoderGenerator_feature_Res(nn.Module):
    def __init__(self, norm_layer, image_size, output_nc, latent_dim=512):
        super(DecoderGenerator_feature_Res, self).__init__()
        # start from B*1024
        latent_size = int(image_size / 32)
        self.latent_size = latent_size
        longsize = 512 * latent_size * latent_size

        activation = nn.ReLU()
        padding_type = 'reflect'
        norm_layer = nn.BatchNorm

        self.fc = nn.Sequential(nn.Linear(in_features=latent_dim, out_features=longsize))
        layers_list = []

        # 176 176
        layers_list.append(ResnetBlock(512, padding_type=padding_type, activation=activation, norm_layer=norm_layer))

        # 22 22
        layers_list.append(DecoderBlock(channel_in=512, channel_out=256, kernel_size=4, padding=1, stride=2, output_padding=0))

        # 176 176
        layers_list.append(ResnetBlock(256, padding_type=padding_type, activation=activation, norm_layer=norm_layer))

        # 44 44
        layers_list.append(DecoderBlock(channel_in=256, channel_out=256, kernel_size=4, padding=1, stride=2, output_padding=0))

        # 176 176
        layers_list.append(ResnetBlock(256, padding_type=padding_type, activation=activation, norm_layer=norm_layer))

        # 88 88
        layers_list.append(DecoderBlock(channel_in=256, channel_out=128, kernel_size=4, padding=1, stride=2, output_padding=0))

        # 176 176
        layers_list.append(ResnetBlock(128, padding_type=padding_type, activation=activation, norm_layer=norm_layer))

        # 176 176
        layers_list.append(DecoderBlock(channel_in=128, channel_out=64, kernel_size=4, padding=1, stride=2, output_padding=0))

        # 176 176
        layers_list.append(ResnetBlock(64, padding_type=padding_type, activation=activation, norm_layer=norm_layer))

        # 352 352
        layers_list.append(DecoderBlock(channel_in=64, channel_out=64, kernel_size=4, padding=1, stride=2, output_padding=0))

        # 176 176
        layers_list.append(ResnetBlock(64, padding_type=padding_type, activation=activation, norm_layer=norm_layer))

        layers_list.append(nn.ReflectionPad2d(2))
        layers_list.append(nn.Conv(64, output_nc, kernel_size=5, padding=0))

        self.conv = nn.Sequential(*layers_list)

        for m in self.modules():
            weights_init_normal(m)

    def execute(self, ten):
        ten = self.fc(ten)
        ten = jt.reshape(ten, (ten.size()[0], 512, self.latent_size, self.latent_size))
        ten = self.conv(ten)

        return ten


# decoder block (used in the decoder)
class DecoderBlock(nn.Module):
    def __init__(self, channel_in, channel_out, kernel_size=4, padding=1, stride=2, output_padding=0, norelu=False):
        super(DecoderBlock, self).__init__()
        layers_list = []
        layers_list.append(nn.ConvTranspose(channel_in, channel_out, kernel_size, padding=padding, stride=stride, output_padding=output_padding))
        layers_list.append(nn.BatchNorm(channel_out, momentum=0.9))
        if (norelu == False):
            layers_list.append(nn.LeakyReLU(1))
        self.conv = nn.Sequential(*layers_list)

    def execute(self, ten):
        ten = self.conv(ten)

        return ten


# encoder block (used in encoder and discriminator)
class EncoderBlock(nn.Module):
    def __init__(self, channel_in, channel_out, kernel_size=7, padding=3, stride=4):
        super(EncoderBlock, self).__init__()
        # convolution to halve the dimensions
        self.conv = nn.Conv(channel_in, channel_out, kernel_size, padding=padding, stride=stride)
        self.bn = nn.BatchNorm(channel_out, momentum=0.9)
        self.relu = nn.LeakyReLU(1)

    def execute(self, ten, out=False, t=False):
        # here we want to be able to take an intermediate output for reconstruction error
        if out:
            ten = self.conv(ten)
            ten_out = ten
            ten = self.bn(ten)
            ten = self.relu(ten)

            return (ten, ten_out)
        else:
            ten = self.conv(ten)
            ten = self.bn(ten)
            ten = self.relu(ten)

            return ten


class GlobalGenerator(nn.Module):

    def __init__(self, input_nc, output_nc, ngf=64, n_downsampling=3, n_blocks=9, norm_layer=nn.BatchNorm, padding_type='reflect'):
        assert (n_blocks >= 0)
        super(GlobalGenerator, self).__init__()
        activation = nn.ReLU()

        model = [nn.ReflectionPad2d(3), nn.Conv(input_nc, ngf, 7, padding=0), norm_layer(ngf), activation]
        ### down sample
        for i in range(n_downsampling):
            mult = (2 ** i)
            model += [nn.Conv((ngf * mult), ((ngf * mult) * 2), 3, stride=2, padding=1), norm_layer(((ngf * mult) * 2)), activation]

        ### resnet blocks
        mult = (2 ** n_downsampling)
        for i in range(n_blocks):
            model += [ResnetBlock((ngf * mult), padding_type=padding_type, activation=activation, norm_layer=norm_layer)]

        ### up sample
        for i in range(n_downsampling):
            mult = (2 ** (n_downsampling - i))
            model += [nn.ConvTranspose((ngf * mult), int(((ngf * mult) / 2)), 3, stride=2, padding=1, output_padding=1), norm_layer(int(((ngf * mult) / 2))), activation]
        model += [nn.ReflectionPad2d(3), nn.Conv(ngf, output_nc, 7, padding=0), nn.Tanh()]
        self.model = nn.Sequential(*model)

        for m in self.modules():
            weights_init_normal(m)

    def execute(self, input):
        return self.model(input)


##############################################################################
# Losses
##############################################################################

class ToTensor:
    def __init__(self):
        pass

    def __call__(self, img):
        from jittor.transform import to_tensor
        return to_tensor(img)


class GANLoss(nn.Module):
    def __init__(self, use_lsgan=True, target_real_label=1.0, target_fake_label=0.0, tensor=ToTensor):
        super(GANLoss, self).__init__()
        self.real_label = target_real_label
        self.fake_label = target_fake_label
        self.real_label_var = None
        self.fake_label_var = None
        self.Tensor = tensor
        self.loss = MSELoss()

    def get_target_tensor(self, input, target_is_real):
        target_tensor = None
        if target_is_real:
            create_label = ((self.real_label_var is None) or (self.real_label_var.numel() != input.numel()))
            if create_label:
                real_tensor = jt.transform.to_tensor(jt.ones(input.shape))
                self.real_label_var = real_tensor.stop_grad()
            target_tensor = self.real_label_var
        else:
            create_label = ((self.fake_label_var is None) or (self.fake_label_var.numel() != input.numel()))
            if create_label:
                fake_tensor = jt.transform.to_tensor(jt.zeros(input.shape))
                self.fake_label_var = fake_tensor.stop_grad()
            target_tensor = self.fake_label_var
        return target_tensor

    def __call__(self, input, target_is_real):
        if isinstance(input[0], list):
            loss = 0
            for input_i in input:
                pred = input_i[(- 1)]
                target_tensor = self.get_target_tensor(pred, target_is_real)
                loss += self.loss(pred, target_tensor)
            return loss
        else:
            target_tensor = self.get_target_tensor(input[(- 1)], target_is_real)
            return self.loss(input[(- 1)], target_tensor)
