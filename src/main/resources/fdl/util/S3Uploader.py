import boto3
import os
from io import BytesIO

class S3Uploader:
    @staticmethod
    def upload_file(image_bytes, args_list):
        filename = os.path.basename(args_list[0])
        s3_path = 'https://s3.us-east-2.amazonaws.com/'
        session = boto3.Session(args_list[7], args_list[8])
        s3 = session.client('s3', region_name=args_list[11], endpoint_url=s3_path)
        s3.upload_fileobj(BytesIO(image_bytes), args_list[9], filename,
                          ExtraArgs={
                              'ContentType': 'image/jpeg',
                              'ACL': 'public-read'
                          })
        s3_url = f"https://{args_list[10]}.cloudfront.net/{filename}"
        return s3_url