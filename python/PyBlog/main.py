from firebase import Firebase

firebase = Firebase(configuration_path="data/serviceAccountKey.json", app_name="my_blog",
                    storage_bucket_name="pyblog-dc97a.appspot.com")
