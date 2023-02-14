from CombineModel_jt import CombineModel
import cv2
import numpy as np
import jittor as jt
import sys

jt.flags.use_cuda = 0
print('FDL - Started.')

try:
    args_list = []
    args = sys.argv[1:]
    for arg in args: args_list.extend(arg.split(" "))
    sys.argv = [sys.argv[0]]
    print('FDL - Properties: ', args_list)

    image_path = './image_input/' + args_list[0]
    print('FDL - Input File:', image_path)

    mat_img = cv2.imread(image_path)
    mat_img = cv2.resize(mat_img, (512, 512), interpolation=cv2.INTER_CUBIC)
    mat_img = cv2.cvtColor(mat_img, cv2.COLOR_RGB2BGR)
    sketch = mat_img.astype(np.uint8)

    combine_model = CombineModel()
    combine_model.sex = int(args_list[6])
    combine_model.part_weight['eye1'] = float(args_list[1])
    combine_model.part_weight['eye2'] = float(args_list[2])
    combine_model.part_weight['nose'] = float(args_list[3])
    combine_model.part_weight['mouth'] = float(args_list[4])
    combine_model.part_weight[''] = float(args_list[5])
    combine_model.predict_shadow(mat_img)

    output_file = './image_output/' + args_list[0]
    cv2.imwrite(output_file, cv2.cvtColor(combine_model.generated, cv2.COLOR_BGR2RGB))
    jt.gc()
    print('FDL - Output File:', output_file)

except Exception as exception:
    print('FDL - Exception Occurred')
    print(exception)

print('FDL - Finished.')
