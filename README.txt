#Ingeniería del Conocimiento

El software que ha descargado ha sido desarrollado por las siguientes personas:
  - David Robles Álvarez
  - Alejandro Campos 
  - Mario Sánchez
  
  
El programa se basa en el lenguaje de Java, en su versión más actual Java 1.8, por lo tanto para su correcto funcionamiento,
hace falta tener instalada esta version.


La interfaz que se brinda al usuario para usar el programa es sencilla, solo dispone de una única venta.
Desde esta, se podrá crear nuevas operaciones matemáticas basadas en otras operaciones básicas como suma o repetición.
Para esto, se debe especificar un nombre de operación, por ejemplo, "Resta".

A continuación una ventana emergente será mostrada al usuario para introducir los parámetros que recibirá
esta nueva operación que se esta creando. Estas operaciones pueden recibir constantes como 'A' o '-A' u operaciones
existentes dentro del programa, como repetición etc.

Una vez que creamos la nueva operación, se generará una clase con el nombre que especificamos al principio del proceso
que contendra el código que hemos introducido mediante la ventana emergente con sus parámetros.


#Información acerca de las clases integradas en el sistema.

En primer lugar, el programa se incia desde la clase 'Ejecuta'. Esta clase tiene un único atributo de la clase 'Controlador'.
Lo único que realiza esta clase es llamar a la clase mencionada anteriormente 'Controlador'.

La clase 'Controlador' se encarga de cargar la lista de operaciones existentes en primer lugar, y despues lanzar la interfaz
diseñada mediante una llamada al constructor de esta clase llamda 'Interfaz'. En esta clase también se crean los ficheros de
las nuevas clases u operaciones que cree el usuario.

La clase 'Interfaz' hace uso de las librerías swing y awt para crear una ventana que permita al usuario usar el programa
mediante una interfaz gráfica facilitándo la comprension del programa.

El resto de clases son 'Suma' y 'Repeticion', las operaciones básicas mediante las cuales el usuario puede crear nuevas
operaciones ms complejas. Cuando el usuario cree, por ejemplo, la operación 'Resta' se creará una clase para dicha operación.
Cuando esto ocurre, se añadiría a este grupo de clases-operaciones, sin embargo solo 'Suma' y 'Repeticion' son consideradas
como básicas, a pesar de que también se podrá utilizar las nuevas clases para generar otras diferentes.
