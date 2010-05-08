@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n12_cupiTwitter
REM Autor: Camilo Alvarez Duran - 19-oct-2009
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

java -ea -classpath ../lib/cupiTwitter.jar;../test/lib/cupiTwitterTest.jar;../test/lib/junit.jar;../test/lib/derby.jar junit.swingui.TestRunner uniandes.cupi2.cupiTwitter.testCliente.MensajeTest
java -ea -classpath ../lib/cupiTwitter.jar;../test/lib/cupiTwitterTest.jar;../test/lib/junit.jar;../test/lib/derby.jar junit.swingui.TestRunner uniandes.cupi2.cupiTwitter.testCliente.MicroBlogTest