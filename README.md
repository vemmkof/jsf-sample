JavaServer Faces
==============

***JavaServer Faces con PrimeFaces, Hibernate y Maven.***

<!-- **Author:** *Víctor Mújica* -->

# Tabla de contenidos
- [JavaServer Faces](#javaserver-faces)
- [Tabla de contenidos](#tabla-de-contenidos)
- [Resumen](#resumen)
- [JSF](#jsf)
  - [¿Qué es una aplicación en JavaServer Faces?](#%c2%bfqu%c3%a9-es-una-aplicaci%c3%b3n-en-javaserver-faces)
  - [Beneficios](#beneficios)
  - [Ciclo de vida](#ciclo-de-vida)
  - [Managed Beans](#managed-beans)
  - [Facelets](#facelets)
  - [Navegación](#navegaci%c3%b3n)
  - [Filtros](#filtros)
- [PrimeFaces](#primefaces)
- [Hibernate](#hibernate)
- [Maven](#maven)
- [Entorno](#entorno)
- [Proyecto](#proyecto)
- [Conclusiones](#conclusiones)

# Resumen
Este documento tiene como objetivo describir lo esencial del framework JavaServer Faces (JSF) para la plataforma Java EE. Además pretende mostrar un proyecto base con Hibernate, PrimeFaces y Maven, desplegado en el servidor de aplicaciones Tomcat. Para más información se recomienda dirigirse a la documentación [Java Platform, Enterprise Edition](https://docs.oracle.com/javaee/7/JEETT.pdf "The Java EE Tutorial").

# JSF
La tecnología JavaServer Faces es un framework de componentes del lado del servidor para construir aplicaciones web basada en Java.

La tecnología JavaServer Faces consta de lo siguiente:

* Una API para representar componentes y administrar su estado; manejo de eventos, validación del lado del servidor y conversión de datos; definición de navegación de la página; soporte para internacionalización y accesibilidad; y proporcionando extensibilidad para todos estos caracteristicas.
* Bibliotecas de etiquetas para agregar componentes a páginas web y para conectar componentes a objetos del lado del servidor.

La tecnología JavaServer Faces proporciona un modelo de programación bien definido y varias bibliotecas de etiquetas. Las bibliotecas de etiquetas contienen manejadores de éstas que implementan los componentes. Estas características alivian significativamente la carga de crear y mantener aplicaciones web con interfaces de usuario (UI) del lado del servidor.

## ¿Qué es una aplicación en JavaServer Faces?
La funcionalidad que provee una aplicación en JSF es similar a cualquier otra aplicación web en Java. Una aplicación típica en JSF incluye las siguientes partes:
*   Un conjunto de páginas web en el que se presentan los componentes.
*   Un conjunto de etiquetas para agregar componentes a la página web.
*   Un conjunto de beans gestionados, que son objetos simple (POJO) gestionados por el contenedor. En una aplicación JavaServer Faces, los beans administrados sirven como beans de respaldo, que definen propiedades y funciones para los componentes de la interfaz de usuario en una página.
*   Un descriptor de implementación web (archivo web.xml).
*   Opcionalmente, uno o más archivos de recursos de configuración de la aplicación, como un archivo faces-config.xml, que se puede usar para definir reglas de navegación de página y configurar beans y otros objetos personalizados, como componentes personalizados.
*   Opcionalmente, un conjunto de objetos personalizados, que pueden incluir componentes personalizados, validadores, convertidores u oyentes, creados por el desarrollador de la aplicación.
*   Opcionalmente, un conjunto de etiquetas personalizadas para representar objetos personalizados en la página.
## Beneficios

Una de las mayores ventajas de la tecnología JavaServer Faces es que ofrece una separación clara entre el comportamiento y la presentación para aplicaciones web. Una aplicación JavaServer Faces puede asignar solicitudes HTTP al manejo de eventos específicos de componentes y administrar componentes como objetos con estado en el servidor. La tecnología JavaServer Faces le permite crear aplicaciones web que implementan la separación de comportamiento y presentación más fina que tradicionalmente ofrecen las arquitecturas de interfaz de usuario del lado del cliente.

La separación de la lógica de la presentación también permite a cada miembro de un equipo de desarrollo de aplicaciones web centrarse en una sola pieza del proceso de desarrollo y proporciona un modelo de programación simple para vincular las piezas. Por ejemplo, los autores de páginas sin experiencia en programación pueden usar etiquetas de tecnología JavaServer Faces en una página web para vincular objetos del lado del servidor sin escribir ningún script.

Otro objetivo importante de la tecnología JavaServer Faces es aprovechar los componentes familiares y los conceptos de nivel web sin limitarlo a una tecnología de scripting o lenguaje de marcado en particular. Las API de tecnología JavaServer Faces se colocan directamente sobre la API de Servlet, como se muestra en la siguiente figura.
<p align="center">
    <img src="https://docs.oracle.com/javaee/7/tutorial/img/jeett_dt_015.png" title="Java Web Application Technologies" width="40%">
</p>

## Ciclo de vida

El ciclo de vida de una aplicación se refiere a las diversas etapas de procesamiento de esa aplicación, desde su inicio hasta su finalización. Todas las aplicaciones tienen ciclos de vida. Durante el ciclo de vida de una aplicación web, se realizan tareas comunes, incluidas las siguientes.

*   Manejo de solicitudes entrantes
*   Parámetros de decodificación
*   Modificar y guardar estado
*   Representación de páginas web en el navegador

El gramework de la aplicación web JavaServer Faces administra las fases del ciclo de vida automáticamente para aplicaciones simples o le permite administrarlas manualmente para aplicaciones más complejas según sea necesario.

Las aplicaciones JavaServer Faces que usan características avanzadas pueden requerir interacción con el ciclo de vida en ciertas fases. Por ejemplo, las aplicaciones Ajax utilizan funciones de procesamiento parcial del ciclo de vida (consulte Procesamiento parcial y renderizado parcial). Una comprensión más clara de las fases del ciclo de vida es clave para crear componentes bien diseñados.

Se presenta una vista simplificada del ciclo de vida de JavaServer Faces, que consta de las dos fases principales de una aplicación web JavaServer Faces, se presenta en A Simple JavaServer Faces Application. Esta sección examina el ciclo de vida de JavaServer Faces con más detalle.

## Managed Beans

Los beans administrados (Managed Beans), son contenedores livianos de objetos (POJO) con requisitos mínimos, admiten un pequeño conjunto de servicios básicos, como inyección de recursos, devoluciones de llamadas del ciclo de vida e interceptores. Los managed beans representan una generalización de la tecnología JavaServer Faces y pueden utilizarse en cualquier lugar de una aplicación Java EE, no solo en módulos web.

La especificación Managed Beans es parte de la especificación de la plataforma Java EE 7 (JSR 342). La plataforma Java EE 7 requiere Managed Beans 1.0.

## Facelets
El término Facelets se refiere al lenguaje de declaración de vista para la tecnología JavaServer Faces. Facelets es parte de la especificación JavaServer Faces y también la tecnología de presentación preferida para crear aplicaciones basadas en la tecnología JavaServer Faces. La tecnología JavaServer Pages (JSP), utilizada anteriormente como tecnología de presentación para JavaServer Faces, no admite todas las nuevas funciones disponibles en JavaServer Faces en la plataforma Java EE 7. La tecnología JSP se considera una tecnología de presentación obsoleta para JavaServer Faces.


856/5000
Facelets es un lenguaje de declaración de página potente pero liviano que se usa para construir vistas de JavaServer Faces usando plantillas de estilo HTML y para construir árboles de componentes. Las características de Facelets incluyen lo siguiente:

* Uso de XHTML para crear páginas web
* Soporte para bibliotecas de etiquetas Facelets además de las bibliotecas de etiquetas JavaServer Faces y JSTL
* Soporte para el lenguaje de expresión (EL)
* Plantillas para componentes y páginas

Las ventajas de Facelets para proyectos de desarrollo a gran escala incluyen las siguientes:

* Soporte para la reutilización de código a través de plantillas y componentes compuestos.
* Extensibilidad funcional de componentes y otros objetos del lado del servidor a través de la personalización
* Tiempo de compilación más rápido.
* Validación EL en tiempo de compilación
* Renderizado de alto rendimiento

En resumen, el uso de Facelets reduce el tiempo y el esfuerzo necesarios para el desarrollo y la implementación.

Las vistas de facelets generalmente se crean como páginas XHTML. Las implementaciones de JavaServer Faces admiten páginas XHTML creadas de conformidad con la definición de tipo de documento de transición (DTD) XHTML, tal como se detalla en http://www.w3.org/TR/xhtml1/#a_dtd_XHTML-1.0-Transitional. Por convención, las páginas web creadas con XHTML tienen una extensión .xhtml.

La tecnología JavaServer Faces admite varias bibliotecas de etiquetas para agregar componentes a una página web. Para admitir el mecanismo de biblioteca de etiquetas JavaServer Faces, Facelets utiliza declaraciones de espacio de nombres (namespace) XML. La siguiente tabla enumera las bibliotecas de etiquetas compatibles con los Facelets.

<table title="Tag Libraries Supported by Facelets">
    <colgroup>
        <col width="13%">
        <col width="*">
        <col width="12%">
        <col width="18%">
        <col width="17%">
    </colgroup>
    <thead>
        <tr>
            <th>Tag Library</th>
            <th>URI</th>
            <th>Prefix</th>
            <th>Example</th>
            <th>Contents</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>
                <p>JavaServer Faces Facelets Tag Library</p>
            </td>
            <td>
                <p><code dir="ltr">http://xmlns.jcp.org/jsf/facelets</code></p>
            </td>
            <td>
                <p><code dir="ltr">ui:</code></p>
            </td>
            <td>
                <p><code dir="ltr">ui:component</code></p>
                <p><code dir="ltr">ui:insert</code></p>
            </td>
            <td>
                <p>Tags for templating</p>
            </td>
        </tr>
        <tr>
            <td>
                <p>JavaServer Faces HTML Tag Library</p>
            </td>
            <td>
                <p><code dir="ltr">http://xmlns.jcp.org/jsf/html</code></p>
            </td>
            <td>
                <p><code dir="ltr">h:</code></p>
            </td>
            <td>
                <p><code dir="ltr">h:head</code></p>
                <p><code dir="ltr">h:body</code></p>
                <p><code dir="ltr">h:outputText</code></p>
                <p><code dir="ltr">h:inputText</code></p>
            </td>
            <td>
                <p>JavaServer Faces component tags for all <code dir="ltr">UIComponent</code> objects</p>
            </td>
        </tr>
        <tr>
            <td>
                <p>JavaServer Faces Core Tag Library</p>
            </td>
            <td>
                <p><code dir="ltr">http://xmlns.jcp.org/jsf/core</code></p>
            </td>
            <td>
                <p><code dir="ltr">f:</code></p>
            </td>
            <td>
                <p><code dir="ltr">f:actionListener</code></p>
                <p><code dir="ltr">f:attribute</code></p>
            </td>
            <td>
                <p>Tags for JavaServer Faces custom actions that are independent of any particular render kit</p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Pass-through Elements Tag Library</p>
            </td>
            <td>
                <p><code dir="ltr">http://xmlns.jcp.org/jsf</code></p>
            </td>
            <td>
                <p><code dir="ltr">jsf:</code></p>
            </td>
            <td>
                <p><code dir="ltr">jsf:id</code></p>
            </td>
            <td>
                <p>Tags to support HTML5-friendly markup</p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Pass-through Attributes Tag Library</p>
            </td>
            <td>
                <p><code dir="ltr">http://xmlns.jcp.org/jsf/passthrough</code></p>
            </td>
            <td>
                <p><code dir="ltr">p:</code></p>
            </td>
            <td>
                <p><code dir="ltr">p:type</code></p>
            </td>
            <td>
                <p>Tags to support HTML5-friendly markup</p>
            </td>
        </tr>
        <tr>
            <td>
                <p>Composite Component Tag Library</p>
            </td>
            <td>
                <p><code dir="ltr">http://xmlns.jcp.org/jsf/composite</code></p>
            </td>
            <td>
                <p><code dir="ltr">cc:</code></p>
            </td>
            <td>
                <p><code dir="ltr">cc:interface</code></p>
            </td>
            <td>
                <p>Tags to support composite components</p>
            </td>
        </tr>
        <tr>
            <td>
                <p>JSTL Core Tag Library</p>
            </td>
            <td>
                <p><code dir="ltr">http://xmlns.jcp.org/jsp/jstl/core</code></p>
            </td>
            <td>
                <p><code dir="ltr">c:</code></p>
            </td>
            <td>
                <p><code dir="ltr">c:forEach</code></p>
                <p><code dir="ltr">c:catch</code></p>
            </td>
            <td>
                <p>JSTL 1.2 Core Tags</p>
            </td>
        </tr>
        <tr>
            <td>
                <p>JSTL Functions Tag Library</p>
            </td>
            <td>
                <p><code dir="ltr">http://xmlns.jcp.org/jsp/jstl/functions</code></p>
            </td>
            <td>
                <p><code dir="ltr">fn:</code></p>
            </td>
            <td>
                <p><code dir="ltr">fn:toUpperCase</code></p>
                <p><code dir="ltr">fn:toLowerCase</code></p>
            </td>
            <td>
                <p>JSTL 1.2 Functions Tags</p>
            </td>
        </tr>
    </tbody>
</table>

## Navegación

El modelo de navegación JavaServer Faces facilita la definición de la navegación de la página y el manejo de cualquier procesamiento adicional que sea necesario para elegir la secuencia en la que se cargan las páginas.

En la tecnología JavaServer Faces, la navegación es un conjunto de reglas para elegir la siguiente página o vista que se mostrará después de una acción de la aplicación, como cuando se hace clic en un botón o enlace.

La navegación puede ser implícita o definida por el usuario. La navegación implícita entra en juego cuando las reglas de navegación definidas por el usuario no están configuradas en los archivos de recursos de configuración de la aplicación.

Cuando agrega un componente como *commandButton* a una página de Facelets y asigna otra página como valor para su propiedad de acción, el controlador de navegación predeterminado intentará hacer coincidir una página adecuada dentro de la aplicación implícitamente. En el siguiente ejemplo, el controlador de navegación predeterminado intentará localizar una página llamada response.xhtml dentro de la aplicación y navegar a ella:

```xml
<h:commandButton value="submit" action="response">
```

Las reglas de navegación definidas por el usuario se declaran en cero o más archivos de recursos de configuración de la aplicación, como *faces-config.xml*, mediante el uso de un conjunto de elementos XML. La estructura predeterminada de una regla de navegación es la siguiente:


```xml
<navigation-rule>
    <description></description
    <from-view-id></from-view-id>
    <navigation-case>
        <from-action></from-action>
        <from-outcome></from-outcome>
        <if></if>
        <to-view-id></to-view-id>
    </navigation-case>
</navigation-rule>
```

Esta regla establece que cuando se activa un componente de comando (como h: commandButton o h: commandLink) en greeting.xhtml, la aplicación navegará desde la página greeting.xhtml a la página response.xhtml si el resultado hace referencia al La etiqueta del componente del botón es un éxito. Aquí hay una etiqueta h: commandButton de greeting.xhtml que especificaría un resultado lógico de éxito:


```xml
<h:commandButton id="submit" value="Submit" action="success"/>
```


## Filtros

# PrimeFaces

# Hibernate

# Maven

# Entorno

# Proyecto

# Conclusiones




<!-- 	<managed-bean> -->
<!-- 		<managed-bean-name>jsfSampleApp</managed-bean-name> -->
<!-- 		<managed-bean-class>com.ipn.escom.JsfSampleApp</managed-bean-class> -->
<!-- 		<managed-bean-scope>request</managed-bean-scope> -->
<!-- 	</managed-bean> -->