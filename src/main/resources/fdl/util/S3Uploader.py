import boto3
from io import BytesIO

class S3Uploader:
    @staticmethod
    def upload_file(image_bytes, filename):
        secret_access_key = 'itVHf8VvgGoP4SZKFfd0s4B8+o93aw5x7YMYBLTY'
        access_key_id = 'AKIA3B6S3JHVS7QXZ3EJ'
        bucket_name = 'facetcher-output-image'
        s3_path = 'https://s3.us-east-2.amazonaws.com'
        session = boto3.Session(access_key_id, secret_access_key)
        s3 = session.client('s3', region_name='us-east-2', endpoint_url=s3_path)
        s3.upload_fileobj(BytesIO(image_bytes), bucket_name, filename,
              ExtraArgs={
                  'ContentType': 'image/jpeg',
                  'ACL': 'public-read'
              })
        s3_url = f"https://d3lpn6btafdjpw.cloudfront.net/{filename}"
        return s3_url