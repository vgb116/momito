@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n8_cupiPosts
REM Autor: Juan David Villa - 18-sep-2009
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------
cd ..
java -classpath ./lib/cupiPosts.jar;./test/lib/cupiPostsTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiPosts.test.CupiPostsTest
java -classpath ./lib/cupiPosts.jar;./test/lib/cupiPostsTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiPosts.test.CategoriaTest
java -classpath ./lib/cupiPosts.jar;./test/lib/cupiPostsTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiPosts.test.PostTest