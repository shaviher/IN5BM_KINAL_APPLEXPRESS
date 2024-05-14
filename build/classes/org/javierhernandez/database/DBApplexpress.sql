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

-------------------------------------------- Cliente

-- Agregar cliente
DELIMITER $$
CREATE PROCEDURE sp_AgregarCliente (IN IDCliente int, IN nitCliente varchar(10), IN nombreCliente varchar(50),
    IN apellidoCliente varchar(50), IN direccionCliente varchar(150), IN telefonoCliente varchar(8), IN correoCliente varchar(45))
BEGIN
    INSERT INTO Clientes (IDCliente, nitCliente, nombreCliente, apellidoCliente, direccionCliente, telefonoCliente, correoCliente)
    VALUES (IDCliente, nitCliente, nombreCliente, apellidoCliente, direccionCliente, telefonoCliente, correoCliente);
END$$
DELIMITER ;

CALL sp_AgregarCliente(1, '1234567890', 'Juan', 'Pérez', 'Calle Principal', '12345678', 'juan@example.com');
CALL sp_AgregarCliente(2, '0987654321', 'Pedro', 'Gómez', 'Avenida Secundaria', '87654321', 'pedro@example.com');
CALL sp_AgregarCliente(3, '5678901234', 'Ana', 'Martínez', 'Calle Secundaria', '34567890', 'ana@example.com');
CALL sp_AgregarCliente(4, '9876543210', 'María', 'López', 'Avenida Central', '87654321', 'maria@example.com');
CALL sp_AgregarCliente(5, '1357924680', 'Carlos', 'García', 'Calle 10', '98765432', 'carlos@example.com');

-- Listar clientes
DELIMITER $$
CREATE PROCEDURE sp_ListarClientes ()
BEGIN
    SELECT IDCliente, nitCliente, nombreCliente, apellidoCliente, direccionCliente, telefonoCliente, correoCliente
    FROM Clientes;
END$$
DELIMITER ;

call sp_ListarClientes();

-- Actualizar cliente
DELIMITER $$
CREATE PROCEDURE sp_ActualizarCliente (IN IDClienteNuevo int, IN nuevoNitCliente varchar(10), IN nuevoNombreCliente varchar(50), 
    IN nuevoApellidoCliente varchar(50), IN nuevaDireccionCliente varchar(150), IN nuevoTelefonoCliente varchar(8), IN nuevoCorreoCliente varchar(45))
BEGIN
    UPDATE Clientes
    SET
        nitCliente = nuevoNitCliente,
        nombreCliente = nuevoNombreCliente,
        apellidoCliente = nuevoApellidoCliente,
        direccionCliente = nuevaDireccionCliente,
        telefonoCliente = nuevoTelefonoCliente,
        correoCliente = nuevoCorreoCliente
    WHERE
        IDCliente = IDClienteNuevo;
END$$
DELIMITER ;

CALL sp_ActualizarCliente(1, '9876543210', 'María', 'López', 'Avenida Central', '87654321', 'maria@example.com');

-- Eliminar Cliente 
DELIMITER $$
CREATE PROCEDURE sp_EliminarCliente (IN IDCliente int)
BEGIN
    DELETE FROM Clientes WHERE IDCliente = IDCliente;
END$$
DELIMITER ;

CALL sp_EliminarCliente(1);


-------------------------------------- proveedores
-- Agregar Proveedor 	
DELIMITER $$
CREATE PROCEDURE sp_AgregarProveedor (IN IDProveedores int, IN nitProveedor varchar(10), IN nombreProveedor varchar(50), IN apellidoProveedor varchar(50), 
IN direccionProveedor varchar(150), IN razonSocial varchar(60), IN contactoPrincipal varchar(100), IN paginaWeb varchar(50))
BEGIN
    INSERT INTO Proveedores (IDProveedores, nitProveedor, nombreProveedor, apellidoProveedor, direccionProveedor, razonSocial, contactoPrincipal, paginaWeb)
    VALUES (IDProveedores, nitProveedor, nombreProveedor, apellidoProveedor, direccionProveedor, razonSocial, contactoPrincipal, paginaWeb);
END$$
DELIMITER ;

CALL sp_AgregarProveedor(1, '1234567890', 'Empresa A', 'Proveedor A', 'Dirección 1', 'Razón Social A', 'Contacto A', 'www.empresaA.com');
CALL sp_AgregarProveedor(2, '9876543210', 'Empresa B', 'Proveedor B', 'Dirección 2', 'Razón Social B', 'Contacto B', 'www.empresaB.com');
CALL sp_AgregarProveedor(3, '1357924680', 'Empresa C', 'Proveedor C', 'Dirección 3', 'Razón Social C', 'Contacto C', 'www.empresaC.com');
CALL sp_AgregarProveedor(4, '2468135790', 'Empresa D', 'Proveedor D', 'Dirección 4', 'Razón Social D', 'Contacto D', 'www.empresaD.com');
CALL sp_AgregarProveedor(5, '3692581470', 'Empresa E', 'Proveedor E', 'Dirección 5', 'Razón Social E', 'Contacto E', 'www.empresaE.com');

-- Listar Proveedores
DELIMITER $$
CREATE PROCEDURE sp_ListarProveedores ()
BEGIN
    SELECT IDProveedores, nitProveedor, nombreProveedor, apellidoProveedor, direccionProveedor, razonSocial, contactoPrincipal, paginaWeb
    FROM Proveedores;
END$$
DELIMITER ;

CALL sp_ListarProveedores();

-- Actualizar Proveedor
DELIMITER $$
CREATE PROCEDURE sp_ActualizarProveedor (IN IDProveedoresNuevo int, IN nuevonitProveedor varchar(10), IN nuevonombreProveedor varchar(50), IN nuevoapellidoProveedor varchar(50), 
IN nuevodireccionProveedor varchar(150), IN nuevorazonSocial varchar(60), IN nuevocontactoPrincipal varchar(100), IN nuevopaginaWeb varchar(50))
BEGIN
    UPDATE Proveedores
    SET
        nitProveedor = nuevonitProveedor,
        nombreProveedor = nuevonombreProveedor,
        apellidoProveedor = nuevoapellidoProveedor,
        direccionProveedor = nuevodireccionProveedor,
        razonSocial = nuevorazonSocial,
        contactoPrincipal = nuevocontactoPrincipal,
        paginaWeb = nuevopaginaWeb
    WHERE
        IDProveedores = IDProveedoresNuevo;
END$$
DELIMITER ;

CALL sp_ActualizarProveedor(1, '9876543210', 'NuevoNombre1', 'NuevoApellido1', 'NuevaDirección1', 'NuevaRazónSocial1', 'NuevoContacto1', 'www.nuevaEmpresa1.com');
CALL sp_ActualizarProveedor(2, '1234567890', 'NuevoNombre2', 'NuevoApellido2', 'NuevaDirección2', 'NuevaRazónSocial2', 'NuevoContacto2', 'www.nuevaEmpresa2.com');
CALL sp_ActualizarProveedor(3, '5432167890', 'NuevoNombre3', 'NuevoApellido3', 'NuevaDirección3', 'NuevaRazónSocial3', 'NuevoContacto3', 'www.nuevaEmpresa3.com');


-- Eliminar Compra 
DELIMITER $$
CREATE PROCEDURE sp_EliminarProveedor (IN IDProveedores int)
BEGIN
    DELETE FROM Proveedores WHERE IDProveedores = IDProveedores;
END$$
DELIMITER ;

CALL sp_EliminarProveedor(3);

-------------------------------------- Compras
-- Agregar Compras
DELIMITER $$
CREATE PROCEDURE sp_AgregarCompras (IN IDCompra int, IN fechaDocumento date, IN descripcion varchar(60),IN totalDocumento decimal(10,2))
BEGIN
    INSERT INTO Compras (IDCompra, fechaDocumento, descripcion, totalDocumento)
    VALUES (IDCompra, fechaDocumento, descripcion, totalDocumento);
END$$
DELIMITER ;

CALL sp_AgregarCompras(1, '2024-05-01', 'Compra de productos A', 100.00);
CALL sp_AgregarCompras(2, '2024-05-02', 'Compra de productos B', 150.50);
CALL sp_AgregarCompras(3, '2024-05-03', 'Compra de productos C', 75.25);
CALL sp_AgregarCompras(4, '2024-05-04', 'Compra de productos D', 200.75);
CALL sp_AgregarCompras(5, '2024-05-05', 'Compra de productos E', 300.00);
CALL sp_AgregarCompras(6, '2024-12-06', 'Compra de productos F', 350.00);
CALL sp_AgregarCompras(7, '2024-02-12', 'Compra de productos G', 180.50);
CALL sp_AgregarCompras(8, '2024-01-03', 'Compra de productos H', 750.00);


-- Listar Compras
DELIMITER $$
CREATE PROCEDURE sp_ListarCompras ()
BEGIN
    SELECT IDCompra, fechaDocumento, descripcion, totalDocumento
    FROM Compras;
END$$
DELIMITER ;

call sp_ListarCompras();

-- Actualizar Compra
DELIMITER $$
CREATE PROCEDURE sp_ActualizarCompra (IN IDCompranuevo int, IN nuevofechaDocumento date, IN nuevodescripcion varchar(60),IN nuevototalDocumento decimal(10,2))
BEGIN
    UPDATE Compras
    SET
        fechaDocumento = nuevofechaDocumento,
        descripcion = nuevodescripcion,
        totalDocumento = nuevototalDocumento
    WHERE
        IDCompra = IDCompraNuevo;
END$$
DELIMITER ;

CALL sp_ActualizarCompra(1, '2024-06-01', 'Nueva descripción para producto A', 120.00);
CALL sp_ActualizarCompra(2, '2024-06-02', 'Nueva descripción para producto B', 160.50);

-- Eliminar Compra 
DELIMITER $$
CREATE PROCEDURE sp_EliminarCompra (IN IDCompra int)
BEGIN
    DELETE FROM Compras WHERE IDCompra = IDCompra;
END$$
DELIMITER ;

CALL sp_EliminarCompra(3);

----------------------------------------------- Tipo de Empleado 
-- Agregar tipo de empleado
DELIMITER $$
CREATE PROCEDURE sp_AgregarTipoProducto (IN idTipoProducto int, IN descripcion varchar(100))
BEGIN
    INSERT INTO TipoProducto (idTipoProducto, descripcion)
    VALUES (idTipoProducto, descripcion);
END$$
DELIMITER ;

CALL sp_AgregarTipoProducto(1, 'Electrónicos');
CALL sp_AgregarTipoProducto(2, 'Ropa');
CALL sp_AgregarTipoProducto(3, 'Alimentos');
CALL sp_AgregarTipoProducto(4, 'Hogar');
CALL sp_AgregarTipoProducto(5, 'Juguetes');

-- Listar TipoProducto
DELIMITER $$
CREATE PROCEDURE sp_ListarTipoProducto ()
BEGIN
    SELECT idTipoProducto, descripcion
    FROM TipoProducto;
END$$
DELIMITER ;

CALL sp_ListarTipoProducto();

-- Actualizar TipoProducto
DELIMITER $$
CREATE PROCEDURE sp_ActualizarTipoProducto (IN idTipoProductoNuevo int, IN nuevodescripcion varchar(100))
BEGIN
    UPDATE TipoProducto
    SET
        descripcion = nuevodescripcion
    WHERE
        idTipoProducto = idTipoProductoNuevo;
END$$

CALL sp_ActualizarTipoProducto(1, 'Electrónicos Mejorados');
CALL sp_ActualizarTipoProducto(2, 'Ropa de Temporada');


-- Eliminar Compra 
DELIMITER $$
CREATE PROCEDURE sp_EliminarTipoProducto (IN idTipoProducto int)
BEGIN
    DELETE FROM TipoProducto WHERE idTipoProducto = idTipoProducto;
END$$
DELIMITER ;

CALL sp_EliminarTipoProducto(3);

-------------------------------------------- CargoEmpleado

-- Agregar tipo de empleado
DELIMITER $$
CREATE PROCEDURE sp_AgregarCargoEmpleado (IN idCargoEmpleado int, IN nombreCargo varchar(100), IN descripcionCargo varchar(100))
BEGIN
    INSERT INTO CargoEmpleado (idCargoEmpleado, nombreCargo, descripcionCargo)
    VALUES (idCargoEmpleado, nombreCargo, descripcionCargo);
END$$
DELIMITER ;

CALL sp_AgregarCargoEmpleado(1, 'Gerente', 'Encargado de supervisar las operaciones generales');
CALL sp_AgregarCargoEmpleado(2, 'Asistente', 'Asistente administrativo');
CALL sp_AgregarCargoEmpleado(3, 'Vendedor', 'Encargado de ventas y atención al cliente');
CALL sp_AgregarCargoEmpleado(4, 'Cajero', 'Responsable del manejo de caja');
CALL sp_AgregarCargoEmpleado(5, 'Técnico', 'Especialista en reparaciones y mantenimiento');

-- Listar TipoProducto
DELIMITER $$
CREATE PROCEDURE sp_ListarCargoEmpleado ()
BEGIN
    SELECT idCargoEmpleado, nombreCargo, descripcionCargo
    FROM CargoEmpleado;
END$$
DELIMITER ;

CALL sp_ListarCargoEmpleado();

-- Actualizar TipoProducto
DELIMITER $$
CREATE PROCEDURE sp_ActualizaRCargoEmpleado (IN idCargoEmpleadonuevo int, IN nuevonombreCargo varchar(100), IN nuevodescripcionCargo varchar(100))
BEGIN
    UPDATE CargoEmpleado
    SET
        nombreCargo = nuevonombreCargo, 
        descripcionCargo = nuevodescripcionCargo
    WHERE
        idCargoEmpleado = idCargoEmpleadonuevo;
END$$

CALL sp_ActualizaRCargoEmpleado(3, 'Vendedor', 'Encargado de ventaS');
CALL sp_ActualizaRCargoEmpleado(4, 'Cajero', 'Responsable del manejo de caja y cheques');


-- Eliminar Compra 
DELIMITER $$
CREATE PROCEDURE sp_EliminarCargoEmpleado (IN idCargoEmpleado int)
BEGIN
    DELETE FROM CargoEmpleado WHERE idCargoEmpleado = idCargoEmpleado;
END$$
DELIMITER ;

CALL sp_EliminarCargoEmpleado(3);







