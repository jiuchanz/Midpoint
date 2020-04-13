import requests,time

URL = "http://localhost:8080/data"
id = 123456789
data = {
    'deviceId' : id,
    'payload' : {
        'car' : 'benz'
    }
}

while True:
    print('post data: ', data)
    r = requests.post(url = URL, json = data)
    print(r.text)
    time.sleep(2)