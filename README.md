# APP Android Studio
***

## Descripción
***
HelloAndroid es una pequeña aplicación desarrollada en Android Studio con el objetivo de hacer uso de algunas caracteristicas del IDE
y del desarrollo para andriod. Es una aplicación netamente de aprendizaje.

## Requerimientos
***
Ninguno en particular

***

## Funcionalidades
***
> Calculadora básica
> Recurso embebido
> Llamado a una web
> Registro -->firebae
> Login -->firebse

***
## Descripcion detallada
Esta app esta desarrollada con el objetivo de poner en practica algunos elementos básicos en el desarrollo para
android, haciendo uso del Andrroi Studio, si bien no tiene una interfaz terminada, por lo menos es una app
absolutamente funcional, a continuación se detallan las funcionalidades incorporadas

1) Main Activity: Esta pantalla principal tiene, una calculadora sencilla con las operaciones suma, resta
multiplicacion y division, ademas de algunos botones para lanzar alguno servicios como
    - Recurso web embebido (en este caso una página de google)
    - Llamado a una web, en este caso se llama a SIAGA
    - Registro: que llama una nuena activity donde el usuario podrá registrarse, para ello esta app tiene
      conexión con firebase con el modulo Authentication
    - Login: Luego del registro el usuario queda inmediatamente logueado, sin embargo posteriormente se puede
       volver a loguear por medio del boton Login

2) Login: Luego del login la app queda en una actividad (SesionIniciada) donde se muestra el correo del usuario
    registrado, ademas de ellos una serie de campos de texto don el usuario podrá, guardar, recuperar o borrar la
    inforacion de usuario la cual es
        (-) Nombres
        (-) Apellidos
        (-) Edad
        (-) Telefono
     estos campos son gestionados por FireBase por el módulo FireStore Database

***
## Actualizaciones para futuras versiones
        (-) Evtar el  error en el login y registro cuando los campos de texto estan vaciones
        (-) Eliminar el botón de cancelar de la activity logueo, pero garantizando un adecuado flujo de trabajo
        (-) llevar las funcionalidades calculadora, recurso embebido, y enlace a web (siaga) a una actividad
        luego del login
        (-) Definitivamente buscar la manera que luego del login, no se pueda devolver a la mainactivity por ningun
        medio a no ser que el usuario cierre la sesion
        (-) Por supuesto mejorar la interfaz


***
Desarrollado por Maolink Software
Agosto 2021
V 1.0


