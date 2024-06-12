-- drop database if exists DBApplexpress;
create database DBApplexpress;
use DBApplexpress;
set global time_zone = "-6:00";
 
create table TipoProducto(
	idTipoProducto int primary key not null,
    descripcion varchar(100)
);
 
create table Compras(
	IDCompra int primary key not null,
    fechaDocumento date,
    descripcion varchar(60),
    totalDocumento decimal(10,2)
);
 
create table Clientes(
	IDCliente int primary key not null,
    nitCliente varchar(10),
    nombreCliente varchar(50),
    apellidoCliente varchar(50),
    direccionCliente varchar(150),
    telefonoCliente varchar(8),
    correoCliente varchar(45)
);
 
create table CargoEmpleado(
	idCargoEmpleado int primary key not null,
    nombreCargo varchar(100),
    descripcionCargo varchar(100)
);

create table Proveedores(
	IDProveedores int primary key not null,
    nitProveedor varchar(10),
    nombreProveedor varchar(50),
    apellidoProveedor varchar(50),
    direccionProveedor varchar(150),
    razonSocial varchar(60),
    contactoPrincipal varchar(100),
    paginaWeb varchar(50)
);

create table TelefonoProveedor(
	IDTelefonoProveedor int primary key not null,
	numeroPincipal varchar(8),
	numeroSecundario varchar(8),
	observaciones varchar(45),
	IDProveedores int,
	foreign key (IDProveedores) references Proveedores (IDProveedores) ON DELETE CASCADE
);

create table EmailProveedor(
	IDEmailProveedor int primary key not null,
	emailproveedor varchar(50),
	descripcion varchar(100),
	IDProveedores int,
	foreign key (IDProveedores) references Proveedores (IDProveedores) ON DELETE CASCADE
);

create table Productos(
	IDProducto int primary key not null,
	descripcionProducto varchar(40),
	precioUnitario decimal(10,2),
	precioDocena decimal(10,2),
	precioMayor decimal(10,2),
	existencia int,
	idTipoProducto int,
	IDProveedores int,
	FOREIGN KEY (idTipoProducto) REFERENCES TipoProducto(idTipoProducto) ON DELETE CASCADE,
	foreign key (IDProveedores) references Proveedores(IDProveedores) ON DELETE CASCADE
);

create table DetalleCompra(
	IDDetalleCompra int primary key not null,
	costoUnitario decimal(10,2),
	cantidad int,
	IDProducto int,
	IDCompra int,
	foreign key (IDProducto) references Productos(IDProducto) ON DELETE CASCADE,
	foreign key (IDCompra) references Compras(IDCompra) ON DELETE CASCADE
);

create table Empleados(
	IDEmpleado int primary key not null,
	nombresEmpleado varchar(50),
	apellidosEmpleado varchar(50),
	sueldo decimal(10,2),
	direccion varchar(150),
	turno varchar(15),
	idCargoEmpleado int,
	foreign key (idCargoEmpleado) references CargoEmpleado(idCargoEmpleado) ON DELETE CASCADE
);

create table Factura(
	IDDeFactura int primary key not null,
	estado varchar(50),
	totalFactura decimal(10,2),
	fechaFactura varchar(45),
	IDCliente int,
	IDEmpleado int,
	foreign key (IDCliente) references Clientes(IDCliente) ON DELETE CASCADE,
	foreign key (IDEmpleado) references Empleados(IDEmpleado) ON DELETE CASCADE
);

create table DetalleFactura(
	IDDetalleFactura int primary key not null,
	precioUnitario decimal(10,2),
	cantidad int,
	IDDeFactura int,
	IDProducto int,
	foreign key (IDDeFactura) references Factura(IDDeFactura) ON DELETE CASCADE,
	foreign key (IDProducto) references Productos(IDProducto) ON DELETE CASCADE
);


    