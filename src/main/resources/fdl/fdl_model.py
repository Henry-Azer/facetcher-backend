from CombineModel_jt import CombineModel
import cv2
import numpy as np
import jittor as jt
import sys
import urllib.request
from util.S3Uploader import S3Uploader

jt.flags.use_cuda = 0
print('FDL - Started.')

try:
    args_list = []
    args = sys.argv[1:]
    for arg in args: args_list.extend(arg.split(" "))
    sys.argv = [sys.argv[0]]
    print('FDL - Properties: ', args_list)

    req = urllib.request.urlopen(args_list[0])
    arr = np.asarray(bytearray(req.read()), dtype=np.uint8)
    print('FDL - Input File: ', args_list[0])

    mat_img = cv2.imdecode(arr, -1)
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

    generated_image = cv2.cvtColor(combine_model.generated, cv2.COLOR_BGR2RGB)
    image_bytes = cv2.imencode('.jpg', generated_image)[1].tobytes()

    s3_url = S3Uploader.upload_file(image_bytes, args_list)
    print('FDL - Output File: ', s3_url)
    jt.gc()

except Exception as exception:
    print('FDL - Exception Occurred')
    print(exception)

print('FDL - Finished.')