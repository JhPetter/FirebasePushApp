# FirebasePushApp
Esta aplicación captura los push enviados desde Firebase FCM
## Configuracion de Firebase
- Creamos el proyecto en [Firebase Console](https://console.firebase.google.com/)
- Descargamos el archivo google-services.json
- Adjuntamos el archivo al proyecto dentro de app/

## Creacion del push
Para crear el push hay dos formas, la primera es hacerlo mediante la consola de firebase, y la segunda es consumiendo el ws de firebase 
El ws es el siguiente: https://fcm.googleapis.com/fcm/send
Debe tener esta estructura el push:
```bash
{ 
 "notification" : {
        "body" : "My notification body", 
        "title" : "This is a new Message",
        "image":"https://i.guim.co.uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d/0_174_3408_2046/master/3408.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=0d3f33fb6aa6e0154b7713a00454c83d"
 },
 "data": {
        "body" : "My notification body", 
        "title" : "This is a new Message",
        "image":"https://i.guim.co.uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d/0_174_3408_2046/master/3408.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=0d3f33fb6aa6e0154b7713a00454c83d"
  },
    "to":"/topics/cleidos"
}
```

Llenar los dos objetos Notification y Data, sirve para capturar los datos cuando el app esta en Background y Foreground




