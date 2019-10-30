# AdMobLibrary
 
El proyecto consiste de la app y su librería "admoblibrary2", para usar la librería se debe ingresar el siguiente código en el MainActivity de la app.

```java
View v = new AdMobGame(this);
setContentView(v);
```
Este código ya se encuentra agregado en el MainActiviy.java del paquete de la app.

Sólo debes descargar, abrir el proyecto en el IDE y ejecutarlo, ahí te divertirás con este básico juego :D.

Los archivos AAR estan donde muestra la imagen de abajo.

![Alt text](https://github.com/Layoneth/AdMobLibrary/blob/master/HOla.png)
        
## Getting Started

Al insertar las líneas de código mencionadas anteriormente podrás ver una interfaz como esta...

![Alt text](https://github.com/Layoneth/AdMobLibrary/blob/master/movil.png)

Al oprimir el botón azul aparecerá el intersticial video y al oprimir el botón verde aparecerá el video bonificado.

Mi idea fue hacer una librería que al implementarse generara ese mini juego donde por ver el interticial video gane 5 puntos, por ver incompleto el video bonificado gane 1 punto y al verlo completo gane 6 puntos.

Estos puntos se ven reflejados en el Label de coins.

El banner aparece automáticamente en la parte inferior del View y no da puntos.

Cabe resaltar que cada vez que el método show() de cada Ad se ejecuta aparece un mensaje flotante en la pantalla que te avisa cuando fue ejecutado este método.

Intenté aplicar los test unitarios pero al crear una UI intenté aplicar test de UI a la interfaz de la librería pero no he encontrado la forma de hacerlo. Si saben como se hacer hacermelo saber por favor.

Quedo al tanto de sus comentarios y me divertí mucho realizando su prueba!.

¡Saludos!

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
