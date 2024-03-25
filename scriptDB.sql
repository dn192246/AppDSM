CREATE DATABASE VetSantaBarbara; 

USE VetSantaBarbara; 

CREATE DATABASE VeterinariaSantaBarbara; 
 
USE VeterinariaSantaBarbara; 
 
CREATE TABLE Pacientes ( 
    PacienteID INT PRIMARY KEY, 
    Nombre VARCHAR(100), 
    Especie VARCHAR(50), 
    Raza VARCHAR(50), 
    FechaNacimiento DATE, 
    HistorialMedico TEXT 
); 
 
CREATE TABLE Doctores ( 
    DoctorID INT PRIMARY KEY, 
    Nombre VARCHAR(100), 
    Especialidad VARCHAR(100), 
    Telefono VARCHAR(20) 
); 
 
 
CREATE TABLE Citas ( 
    CitaID INT PRIMARY KEY, 
    FechaHora DATETIME, 
    PacienteID INT, 
    DoctorID INT, 
    Motivo TEXT, 
    FOREIGN KEY (PacienteID) REFERENCES Pacientes(PacienteID), 
    FOREIGN KEY (DoctorID) REFERENCES Doctores(DoctorID) 
); 
 
 
CREATE TABLE Usuarios ( 
    UsuarioID INT PRIMARY KEY, 
    NombreUsuario VARCHAR(50), 
    Contrasena VARCHAR(10) 
); 
