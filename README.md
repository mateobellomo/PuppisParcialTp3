# 2023-2C-TP3-A
# PUPPIESPARCIALTP3  
Puppis es una aplicación móvil creada para conectar mascotas sin hogar con personas que buscan tener compañia en su casa.

La aplicacion esta desarrollada en Kotlin con un minimo de Android 5.0 (Lollipop).



## Integrantes
Paola Yanina Quiñonez, Mateo Agustin Bellomo, Camila Belén Szesko, Francisco Javier Ezequiel Veron y Camila Ingberg

## Preguntas

**En el caso que se pida extender la app para otros tipos de mascotas, por ejemplo, gatos, ¿la app es flexible? ¿Qué cambios realizaría?**
```
La app tiene la suficiente abstracción para que la mayoría de mascotas puedan usar la entidad de PetEntity, ya que tiene atributos genéricos de la mayoría de mascotas como pueden ser gatos, aves o roedores.
```


**¿Qué tipo de arquitectura usaron y por qué? ¿Podría mejorarla?**
```
Usamos una arquitectura que agrupa los archivos según componente de la app, por ejemplo la carpeta de fragments, viewModels, etc.
Separamos las responsabilidades según los componentes de la app, dejando a los fragments únicamente la interacción con el IU, el almacenamiento de datos / entidades en un view model compartido entre los fragmentos, la configuración del usuario en SharedPreferences, etc.
Usamos el dominio para agrupar los casos de uso / funciones más utilizadas en la app y por último usamos la inyección de dependencia para evitar la creación de objetos, logrando una mayor abstracción, para que se pueda cambiar la fuente de datos usada.
Nuestro paso para mejorar la app sería lograr una persistencia de los datos / entidades mediante repositorios, dándole abstracción al almacenamiento, para poder usar cualquier almacenamiento de datos. Empezamos con la implementación de Room pero no logramos terminarlo.
```

**¿Qué mejoras detectan que podrían realizarle a la app?**
```
 - Hacer la persistencia de datos para evitar que cuando la pérdida de datos al momento que se destruye la app.
 - Darle funcionalidad al icono de campana / notificación del action bar.
 - En el formulario poder subir imágenes que el usuario tenga guardadas en la PC.
 - Hacer un registro del usuario.
 - Agregar configuraciones de la app como por ejemplo idioma, notificaciones, etc.
 - Una diferenciación entre usuario común y un refugio.
```
