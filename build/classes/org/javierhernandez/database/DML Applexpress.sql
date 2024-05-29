use DBApplexpress;
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

-- Listar clientes
DELIMITER $$
CREATE PROCEDURE sp_ListarClientes ()
BEGIN
    SELECT IDCliente, nitCliente, nombreCliente, apellidoCliente, direccionCliente, telefonoCliente, correoCliente
    FROM Clientes;
END$$
DELIMITER ;

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

-- Eliminar Cliente 
DELIMITER $$
CREATE PROCEDURE sp_EliminarCliente (IN IDCliente int)
BEGIN
    DELETE FROM Clientes WHERE IDCliente = IDCliente;
END$$
DELIMITER ;

-------------------------------------- Proveedor
-- Agregar Proveedor 	
DELIMITER $$
CREATE PROCEDURE sp_AgregarProveedor (IN IDProveedores int, IN nitProveedor varchar(10), IN nombreProveedor varchar(50), IN apellidoProveedor varchar(50), 
IN direccionProveedor varchar(150), IN razonSocial varchar(60), IN contactoPrincipal varchar(100), IN paginaWeb varchar(50))
BEGIN
    INSERT INTO Proveedores (IDProveedores, nitProveedor, nombreProveedor, apellidoProveedor, direccionProveedor, razonSocial, contactoPrincipal, paginaWeb)
    VALUES (IDProveedores, nitProveedor, nombreProveedor, apellidoProveedor, direccionProveedor, razonSocial, contactoPrincipal, paginaWeb);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_ListarProveedores ()
BEGIN
    SELECT * FROM Proveedores;
END$$
DELIMITER ;


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

-- Eliminar Proveedor 
DELIMITER $$
CREATE PROCEDURE sp_EliminarProveedor (IN IDProveedores int)
BEGIN
    DELETE FROM Proveedores WHERE IDProveedores = IDProveedores;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarProveedor(IN IDProveedores int)
BEGIN
    SELECT * FROM Proveedores WHERE IDProveedores = IDProveedores;
END$$
DELIMITER ;


-------------------------------------- Compras
-- Agregar Compras
DELIMITER $$
CREATE PROCEDURE sp_AgregarCompras (IN IDCompra int, IN fechaDocumento date, IN descripcion varchar(60),IN totalDocumento decimal(10,2))
BEGIN
    INSERT INTO Compras (IDCompra, fechaDocumento, descripcion, totalDocumento)
    VALUES (IDCompra, fechaDocumento, descripcion, totalDocumento);
END$$
DELIMITER ;

-- Listar Compras
DELIMITER $$
CREATE PROCEDURE sp_ListarCompras ()
BEGIN
    SELECT IDCompra, fechaDocumento, descripcion, totalDocumento
    FROM Compras;
END$$
DELIMITER ;

-- Actualizar Compras
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

-- Eliminar Compra 
DELIMITER $$
CREATE PROCEDURE sp_EliminarCompra (IN IDCompra int)
BEGIN
    DELETE FROM Compras WHERE IDCompra = IDCompra;
END$$
DELIMITER ;

----------------------------------------------- Tipo Producto
-- Agregar Tipo Producto
DELIMITER $$
CREATE PROCEDURE sp_AgregarTipoProducto (IN idTipoProducto int, IN descripcion varchar(100))
BEGIN
    INSERT INTO TipoProducto (idTipoProducto, descripcion)
    VALUES (idTipoProducto, descripcion);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_ListarTipoProducto ()
BEGIN
    SELECT * FROM TipoProducto;
END$$
DELIMITER ;

-- Actualizar Tipo Producto
DELIMITER $$
CREATE PROCEDURE sp_ActualizarTipoProducto (IN idTipoProductoNuevo int, IN nuevodescripcion varchar(100))
BEGIN
    UPDATE TipoProducto
    SET
        descripcion = nuevodescripcion
    WHERE
        idTipoProducto = idTipoProductoNuevo;
END$$

-- EliminarTipo Producto
DELIMITER $$
CREATE PROCEDURE sp_EliminarTipoProducto (IN idTipoProducto int)
BEGIN
    DELETE FROM TipoProducto WHERE idTipoProducto = idTipoProducto;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarTipoProducto(IN idTipoProducto int)
BEGIN
    SELECT * FROM TipoProducto WHERE idTipoProducto = idTipoProducto;
END$$
DELIMITER ;


-------------------------------------------- CargoEmpleado
-- Agregar CargoEmpleado
DELIMITER $$
CREATE PROCEDURE sp_AgregarCargoEmpleado (IN idCargoEmpleado int, IN nombreCargo varchar(100), IN descripcionCargo varchar(100))
BEGIN
    INSERT INTO CargoEmpleado (idCargoEmpleado, nombreCargo, descripcionCargo)
    VALUES (idCargoEmpleado, nombreCargo, descripcionCargo);
END$$
DELIMITER ;

-- Listar CargoEmpleado
DELIMITER $$
CREATE PROCEDURE sp_ListarCargoEmpleado ()
BEGIN
    SELECT idCargoEmpleado, nombreCargo, descripcionCargo
    FROM CargoEmpleado;
END$$
DELIMITER ;

-- Actualizar CargoEmpleado
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

-- Eliminar CargoEmpleado
DELIMITER $$
CREATE PROCEDURE sp_EliminarCargoEmpleado (IN idCargoEmpleado int)
BEGIN
    DELETE FROM CargoEmpleado WHERE idCargoEmpleado = idCargoEmpleado;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarCargoEmpleado(IN idCargoEmpleado int)
BEGIN
    SELECT * FROM CargoEmpleado WHERE idCargoEmpleado = idCargoEmpleado;
END$$
DELIMITER ;

-------------------------------------------- Telefono
-- Agregar telefono

DELIMITER $$
CREATE PROCEDURE sp_AgregarTelefono(IN IDTelefonoProveedor INT, IN numeroPincipal VARCHAR(8), IN numeroSecundario VARCHAR(8), 
    IN observaciones VARCHAR(45), IN IDProveedores INT)
BEGIN
    INSERT INTO TelefonoProveedor (IDTelefonoProveedor, numeroPincipal, numeroSecundario, observaciones, IDProveedores)
    VALUES (IDTelefonoProveedor, numeroPincipal, numeroSecundario, observaciones, IDProveedores);
END $$
DELIMITER ;

-- listar telefono
DELIMITER $$
CREATE PROCEDURE sp_ListarTelefonoProveedor ()
BEGIN
    SELECT IDTelefonoProveedor, numeroPincipal, numeroSecundario, observaciones, IDProveedores
    FROM TelefonoProveedor;
END$$
DELIMITER ;


-- Actualizar telefono 
DELIMITER $$
CREATE PROCEDURE sp_ActualizarTelefono( IN nuevoIDTelefonoProveedor INT, IN nuevonumeroPincipal VARCHAR(8), 
IN nuevonumeroSecundario VARCHAR(8), IN nuevoobservaciones VARCHAR(45), IN nuevoIDProveedores INT)
BEGIN
    UPDATE TelefonoProveedor
    SET numeroPincipal = nuevonumeroPincipal, 
        numeroSecundario = nuevonumeroSecundario, 
        observaciones = nuevoobservaciones,
        IDProveedores = nuevoIDProveedores
    WHERE IDTelefonoProveedor = nuevoIDTelefonoProveedor;
END $$
DELIMITER ;

-- eliminar telefono
DELIMITER $$
CREATE PROCEDURE sp_EliminarTelefono(IN IDTelefonoProveedor INT)
BEGIN
    DELETE FROM TelefonoProveedor WHERE IDTelefonoProveedor = IDTelefonoProveedor;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarTelefonoProveedor(IN IDTelefonoProveedor int)
BEGIN
    SELECT * FROM TelefonoProveedor WHERE IDTelefonoProveedor = IDTelefonoProveedor;
END$$
DELIMITER ;

----------------------------------------- Email
-- Agregar Email
DELIMITER $$
CREATE PROCEDURE sp_AgregarEmail( IN IDEmailProveedor INT, IN emailproveedor VARCHAR(50), 
IN descripcion VARCHAR(100), IN IDProveedores INT)
BEGIN
    INSERT INTO EmailProveedor (IDEmailProveedor, emailproveedor, descripcion, IDProveedores)
    VALUES (IDEmailProveedor, emailproveedor, descripcion, IDProveedores);
END $$
DELIMITER ;

-- Listar Email
DELIMITER $$
CREATE PROCEDURE sp_ListarEmail()
BEGIN
    SELECT IDEmailProveedor, emailproveedor, descripcion, IDProveedores
    FROM EmailProveedor;
END $$
DELIMITER ;

-- Actualizar Email
DELIMITER &&
CREATE PROCEDURE sp_ActualizarEmail(IN nuevoIDEmailProveedor INT, IN nuevoemailproveedor VARCHAR(50), 
IN nuevodescripcion VARCHAR(100), IN nuevoIDProveedores INT)
BEGIN
    UPDATE EmailProveedor
    SET emailproveedor = nuevoemailproveedor, 
        descripcion = nuevodescripcion,
        IDProveedores = nuevoIDProveedores
    WHERE IDEmailProveedor = nuevoIDEmailProveedor;
END &&
DELIMITER ;

-- Eliminar Email
DELIMITER &&
CREATE PROCEDURE sp_EliminarEmail(IN IDEmailProveedor INT)
BEGIN
    DELETE FROM EmailProveedor WHERE IDEmailProveedor = IDEmailProveedor;
END &&
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarEmailProveedor(IN IDEmailProveedor int)
BEGIN
    SELECT * FROM EmailProveedor WHERE IDTelefonoProveedor = IDEmailProveedor;
END$$
DELIMITER ;

----------------------------------------- Productos
-- Agregar Producto
DELIMITER $$
CREATE PROCEDURE sp_AgregarProducto(IN IDProducto INT, IN descripcionProducto VARCHAR(40), IN precioUnitario DECIMAL(10,2), 
IN precioDocena DECIMAL(10,2), IN precioMayor DECIMAL(10,2), IN existencia INT, IN idTipoProducto INT, IN IDProveedores INT)
BEGIN
    INSERT INTO Productos (IDProducto, descripcionProducto, precioUnitario, precioDocena, precioMayor, existencia, idTipoProducto, IDProveedores)
    VALUES (IDProducto, descripcionProducto, precioUnitario, precioDocena, precioMayor, existencia, idTipoProducto, IDProveedores);
END $$
DELIMITER ;

-- Listar Producto
DELIMITER $$
CREATE PROCEDURE sp_ListarProducto()
BEGIN
    SELECT IDProducto, descripcionProducto, precioUnitario, precioDocena, precioMayor, existencia, idTipoProducto, IDProveedores
    FROM Productos;
END $$
DELIMITER ;

-- Actualizar Producto
DELIMITER &&
CREATE PROCEDURE sp_ActualizarProducto(IN nuevoIDProducto INT, IN nuevodescripcionProducto VARCHAR(40), IN nuevoprecioUnitario DECIMAL(10,2),
IN nuevoprecioDocena DECIMAL(10,2), IN nuevoprecioMayor DECIMAL(10,2), IN nuevoexistencia INT, IN nuevoidTipoProducto INT, IN nuevoIDProveedores INT)
BEGIN
    UPDATE Productos
    SET descripcionProducto = nuevodescripcionProducto, 
        precioUnitario = nuevoprecioUnitario, 
        precioDocena = nuevoprecioDocena, 
        precioMayor = nuevoprecioMayor, 
        existencia = nuevoexistencia,
        idTipoProducto = nuevoidTipoProducto,
        IDProveedores = nuevoIDProveedores
    WHERE IDProducto = nuevoIDProducto;
END &&
DELIMITER ;

-- Eliminar Producto 
DELIMITER &&
CREATE PROCEDURE sp_eliminarproducto(IN IDProducto INT)
BEGIN
    DELETE FROM Productos WHERE IDProducto = IDProducto;
END &&
DELIMITER ;


-------------- Empleado

-- Agregar Empleado
DELIMITER $$
CREATE PROCEDURE sp_AgregarEmpleado(IN IDEmpleado INT, IN nombresEmpleado VARCHAR(50), IN apellidosEmpleado VARCHAR(50),
IN sueldo DECIMAL(10,2), IN direccion VARCHAR(150), IN turno VARCHAR(15), IN idCargoEmpleado INT)
BEGIN
    INSERT INTO Empleados (IDEmpleado, nombresEmpleado, apellidosEmpleado, sueldo, direccion, turno, idCargoEmpleado)
    VALUES (IDEmpleado, nombresEmpleado, apellidosEmpleado, sueldo, direccion, turno, idCargoEmpleado);
END $$
DELIMITER ;

-- Listar Empleado 
DELIMITER &&
CREATE PROCEDURE sp_ListarEmpleado()
BEGIN
    SELECT IDEmpleado, nombresEmpleado, apellidosEmpleado, sueldo, direccion, turno, idCargoEmpleado
    FROM Empleados;
END &&
DELIMITER ;

-- Update
DELIMITER $$
CREATE PROCEDURE sp_ActualizarEmpleado(IN nuevoIDEmpleado INT, IN nuevonombresEmpleado VARCHAR(50),
IN nuevoapellidosEmpleado VARCHAR(50), IN nuevosueldo DECIMAL(10,2), IN nuevodireccion VARCHAR(150), IN nuevoturno VARCHAR(15), IN nuevoidCargoEmpleado INT)
BEGIN
    UPDATE Empleados
    SET nombresEmpleado = nuevonombresEmpleado, 
        apellidosEmpleado = nuevoapellidosEmpleado, 
        sueldo = nuevosueldo, 
        direccion = nuevodireccion,
        turno = nuevoturno,
        idCargoEmpleado = nuevoidCargoEmpleado
    WHERE IDEmpleado = nuevoIDEmpleado;
END $$
DELIMITER ;

-- Delete
DELIMITER $$
CREATE PROCEDURE sp_EliminarEmpleado(IN IDEmpleado INT)
BEGIN
    DELETE FROM Empleados WHERE IDEmpleado = IDEmpleado;
END $$
DELIMITER ;

-------------------- Factura
-- Create
DELIMITER $$
CREATE PROCEDURE sp_AgregarFactura(IN IDDeFactura INT, IN estado VARCHAR(50),
IN totalFactura DECIMAL(10,2), IN fechaFactura VARCHAR(45), IN IDCliente INT, IN IDEmpleado INT)
BEGIN
    INSERT INTO Factura (IDDeFactura, estado, totalFactura, fechaFactura, IDCliente, IDEmpleado)
    VALUES (IDDeFactura, estado, totalFactura, fechaFactura, IDCliente, IDEmpleado);
END $$
DELIMITER ;

-- Read
DELIMITER $$
CREATE PROCEDURE sp_ListarFactura()
BEGIN
    SELECT IDDeFactura, estado, totalFactura, fechaFactura, IDCliente, IDEmpleado
    FROM Factura;
END $$
DELIMITER ;

-- Update
DELIMITER $$
CREATE PROCEDURE sp_ActualizarFactura( IN nuevoIDDeFactura INT, IN nuevoestado VARCHAR(50), IN nuevototalFactura DECIMAL(10,2),
IN nuevofechaFactura VARCHAR(45), IN nuevoIDCliente INT, IN nuevoIDEmpleado INT)
BEGIN
    UPDATE Factura
    SET estado = nuevoestado, 
        totalFactura = nuevototalFactura,
        fechaFactura = nuevofechaFactura,
        IDCliente = nuevoIDCliente,
        IDEmpleado = nuevoIDEmpleado
    WHERE IDDeFactura = nuevoIDDeFactura;
END $$
DELIMITER ;

-- Delete
DELIMITER $$
CREATE PROCEDURE sp_EliminarFactura(IN IDDeFactura INT)
BEGIN
    DELETE FROM Factura WHERE IDDeFactura = IDDeFactura;
END $$
DELIMITER ;



-- traer el precio unitario
delimiter //
CREATE FUNCTION fn_PrecioUnitario(IDProducto VARCHAR(15)) RETURNS DECIMAL(10,2)
deterministic
BEGIN
	DECLARE precio DECIMAL(10,2);
	SET precio= (SELECT DetalleCompra.costoUnitario FROM DetalleCompra
    WHERE DetalleCompra.IDProducto = IDProducto);
	RETURN precio;
END //

DELIMITER ;


-- total compra
DELIMITER //
CREATE FUNCTION fn_TotalCompra(totalDocumento INT) RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE sumatoria DECIMAL(10,2);
    
    SET sumatoria = (SELECT sum(cantidad*costoUnitario) FROM DetalleCompra 
					WHERE IDCompra = IDCompra) ;
    RETURN sumatoria;
END //
DELIMITER ;

-- Precios Detalle factura
delimiter //
create trigger tr_insertarPreciosDetalleFactura_Before_Insert
before insert on DetalleFactura
for each row
begin
    declare total decimal(10,2);

    set new.precioUnitario = (select precioUnitario from Productos
                              where Productos.IDProducto = new.IDProducto);

end //
delimiter ;

-- insertar precios en Productos
delimiter //
create trigger tr_insertarPreciosProductos_after_Insert
after insert on DetalleCompra
for each row
begin
    call sp_actualizarPreciosProductos(new.IDProducto, 
                                       (fn_TraerPrecioUnitario(new.IDProducto) + (fn_TraerPrecioUnitario(new.IDProducto) * 0.40)),
                                       (fn_TraerPrecioUnitario(new.IDProducto) + (fn_TraerPrecioUnitario(new.IDProducto) * 0.35)),
                                       (fn_TraerPrecioUnitario(new.IDProducto) + (fn_TraerPrecioUnitario(new.IDProducto) * 0.25)),
                                       new.cantidad);
end //
delimiter ;

-- insertar total compra
delimiter //
create trigger tr_insertarTotalCompra_After_Insert
after insert on DetalleCompra
for each row
begin
    declare total decimal(10,2);

    set total = (select sum(costoUnitario * cantidad) from DetalleCompra where DetalleCompra.IDCompra = new.IDCompra);

    call sp_actualizarComprasTotal(new.IDCompra, total);
end //
delimiter ;

-- insertar total factura
delimiter //
create trigger tr_insertarTotalFactura_After_Insert
after insert on DetalleFactura
for each row
begin
    declare total decimal(10,2);

    set total = (select sum(precioUnitario * cantidad) from DetalleFactura where DetalleFactura.IDDetalleFactura = new.IDDetalleFactura);

    call sp_actualizarFacturaTotal(new.IDDetalleFactura, total);
end //
delimiter ;

CALL sp_AgregarCliente(1, '1234567890', 'Juan', 'Pérez', 'Calle Principal', '12345678', 'juan@example.com');
CALL sp_AgregarCliente(2, '0987654321', 'Pedro', 'Gómez', 'Avenida Secundaria', '87654321', 'pedro@example.com');
CALL sp_AgregarCliente(3, '5678901234', 'Ana', 'Martínez', 'Calle Secundaria', '34567890', 'ana@example.com');
CALL sp_AgregarCliente(4, '9876543210', 'María', 'López', 'Avenida Central', '87654321', 'maria@example.com');
CALL sp_AgregarCliente(5, '1357924680', 'Carlos', 'García', 'Calle 10', '98765432', 'carlos@example.com');
call sp_ListarClientes();
CALL sp_ActualizarCliente(1, '9876543210', 'María', 'López', 'Avenida Central', '87654321', 'maria@example.com');
-- CALL sp_EliminarCliente(1);

CALL sp_AgregarProveedor(1, '1234567890', 'Empresa A', 'Proveedor A', 'Dirección 1', 'Razón Social A', 'Contacto A', 'www.empresaA.com');
CALL sp_AgregarProveedor(2, '9876543210', 'Empresa B', 'Proveedor B', 'Dirección 2', 'Razón Social B', 'Contacto B', 'www.empresaB.com');
CALL sp_AgregarProveedor(3, '1357924680', 'Empresa C', 'Proveedor C', 'Dirección 3', 'Razón Social C', 'Contacto C', 'www.empresaC.com');
CALL sp_AgregarProveedor(4, '2468135790', 'Empresa D', 'Proveedor D', 'Dirección 4', 'Razón Social D', 'Contacto D', 'www.empresaD.com');
CALL sp_AgregarProveedor(5, '3692581470', 'Empresa E', 'Proveedor E', 'Dirección 5', 'Razón Social E', 'Contacto E', 'www.empresaE.com');
CALL sp_ListarProveedores();
CALL sp_BuscarProveedor(1);
CALL sp_ActualizarProveedor(5, '9876543210', 'NuevoNombre1', 'NuevoApellido1', 'NuevaDirección1', 'NuevaRazónSocial1', 'NuevoContacto1', 'www.nuevaEmpresa1.com');
CALL sp_ActualizarProveedor(2, '1234567890', 'NuevoNombre2', 'NuevoApellido2', 'NuevaDirección2', 'NuevaRazónSocial2', 'NuevoContacto2', 'www.nuevaEmpresa2.com');
CALL sp_ActualizarProveedor(3, '5432167890', 'NuevoNombre3', 'NuevoApellido3', 'NuevaDirección3', 'NuevaRazónSocial3', 'NuevoContacto3', 'www.nuevaEmpresa3.com');
-- CALL sp_EliminarProveedor(3);

CALL sp_AgregarCompras(1, '2024-05-01', 'Compra de productos A', 100.00);
CALL sp_AgregarCompras(2, '2024-05-02', 'Compra de productos B', 150.50);
CALL sp_AgregarCompras(3, '2024-05-03', 'Compra de productos C', 75.25);
CALL sp_AgregarCompras(4, '2024-05-04', 'Compra de productos D', 200.75);
CALL sp_AgregarCompras(5, '2024-05-05', 'Compra de productos E', 300.00);
CALL sp_AgregarCompras(6, '2024-12-06', 'Compra de productos F', 350.00);
CALL sp_AgregarCompras(7, '2024-02-12', 'Compra de productos G', 180.50);
CALL sp_AgregarCompras(8, '2024-01-03', 'Compra de productos H', 750.00);
call sp_ListarCompras();
CALL sp_ActualizarCompra(1, '2024-06-01', 'Nueva descripción para producto A', 120.00);
CALL sp_ActualizarCompra(2, '2024-06-02', 'Nueva descripción para producto B', 160.50);
-- CALL sp_EliminarCompra(3);

CALL sp_AgregarTipoProducto(1, 'Electrónicos');
CALL sp_AgregarTipoProducto(2, 'Ropa');
CALL sp_AgregarTipoProducto(3, 'Alimentos');
CALL sp_AgregarTipoProducto(4, 'Hogar');
CALL sp_AgregarTipoProducto(5, 'Juguetes');
CALL sp_BuscarTipoProducto(1);
CALL sp_ListarTipoProducto();
CALL sp_ActualizarTipoProducto(1, 'Electrónicos Mejorados');
CALL sp_ActualizarTipoProducto(2, 'Ropa de Temporada');
-- CALL sp_EliminarTipoProducto(3);

CALL sp_AgregarCargoEmpleado(1, 'Gerente', 'Encargado de supervisar las operaciones generales');
CALL sp_AgregarCargoEmpleado(2, 'Asistente', 'Asistente administrativo');
CALL sp_AgregarCargoEmpleado(3, 'Vendedor', 'Encargado de ventas y atención al cliente');
CALL sp_AgregarCargoEmpleado(4, 'Cajero', 'Responsable del manejo de caja');
CALL sp_AgregarCargoEmpleado(5, 'Técnico', 'Especialista en reparaciones y mantenimiento');
CALL sp_ListarCargoEmpleado();
CALL sp_ActualizaRCargoEmpleado(3, 'Vendedor', 'Encargado de ventaS');
CALL sp_ActualizaRCargoEmpleado(4, 'Cajero', 'Responsable del manejo de caja y cheques');
-- CALL sp_EliminarCargoEmpleado(3);

CALL sp_AgregarTelefono(1, "34768912", "12094576", "Caso de emergencia telefono 2", 1);
CALL sp_AgregarTelefono(2, '34768912', '12094576', 'Caso de emergencia telefono 2', 2);
CALL sp_AgregarTelefono(3, '12345678', '87654321', 'Telefono secundario para contacto', 4);
CALL sp_AgregarTelefono(4, '23456789', '98765432', 'Contacto de emergencia', 3);
CALL sp_AgregarTelefono(5, '34567890', '09876543', 'Telefono principal de la oficina', 5);
CALL sp_AgregarTelefono(6, '45678901', '10987654', 'Telefono personal del proveedor', 2);
CALL sp_ListarTelefonoProveedor();
CALL sp_ActualizarTelefono(1, '34768912', '98765432', 'Actualización de contacto secundario', 1);
CALL sp_ActualizarTelefono(2, '12345678', '87654321', 'Actualización de teléfono principal', 1);
-- CALL sp_EliminarTelefono(3);

CALL sp_AgregarEmail(1, 'proveedor1@example.com', 'Email principal del proveedor 1', 1);
CALL sp_AgregarEmail(2, 'proveedor2@example.com', 'Email principal del proveedor 2', 1);
CALL sp_AgregarEmail(3, 'proveedor3@example.com', 'Email secundario del proveedor 3', 2);
CALL sp_AgregarEmail(4, 'proveedor4@example.com', 'Email de contacto del proveedor 4', 2);
CALL sp_AgregarEmail(5, 'proveedor5@example.com', 'Email principal del proveedor 5', 3);
CALL sp_AgregarEmail(6, 'proveedor6@example.com', 'Email secundario del proveedor 6', 3);
CALL sp_AgregarEmail(7, 'proveedor7@example.com', 'Email de soporte del proveedor 7', 4);
CALL sp_AgregarEmail(8, 'proveedor8@example.com', 'Email principal del proveedor 8', 4);
CALL sp_ListarEmail();
CALL sp_ActualizarEmail(1, 'actualizado1@example.com', 'Email actualizado del proveedor 1', 1);
CALL sp_ActualizarEmail(4, 'actualizado4@example.com', 'Email actualizado del proveedor 4', 2);
CALL sp_ActualizarEmail(7, 'actualizado7@example.com', 'Email actualizado del proveedor 7', 4);
-- CALL sp_EliminarEmail(3);

CALL sp_AgregarProducto(1, 'Producto 1', 10.50, 100.00, 500.00, 50, 1, 1);
CALL sp_AgregarProducto(2, 'Producto 2', 15.75, 120.00, 600.00, 30, 2, 2);
CALL sp_AgregarProducto(3, 'Producto 3', 20.00, 150.00, 750.00, 40, 1, 3);
CALL sp_AgregarProducto(4, 'Producto 4', 12.25, 110.00, 550.00, 60, 3, 1);
CALL sp_AgregarProducto(5, 'Producto 5', 18.90, 130.00, 650.00, 20, 2, 2);
CALL sp_ListarProducto();
CALL sp_ActualizarProducto(1, 'Nuevo Producto 1', 11.75, 105.00, 525.00, 55, 2, 3);
CALL sp_ActualizarProducto(3, 'Nuevo Producto 3', 22.50, 160.00, 800.00, 45, 1, 2);
CALL sp_ActualizarProducto(5, 'Nuevo Producto 5', 19.75, 140.00, 700.00, 25, 3, 1);
-- CALL sp_eliminarproducto(2);

CALL sp_AgregarEmpleado(1, 'Juan', 'Perez', 1500.00, 'Calle Principal 123', 'Mañana', 1);
CALL sp_AgregarEmpleado(2, 'Maria', 'Gonzalez', 1800.00, 'Avenida Central 456', 'Tarde', 2);
CALL sp_AgregarEmpleado(3, 'Pedro', 'Diaz', 2000.00, 'Plaza Mayor 789', 'Noche', 1);
CALL sp_AgregarEmpleado(4, 'Ana', 'Martinez', 1700.00, 'Callejón Secreto 10', 'Mañana', 3);
CALL sp_AgregarEmpleado(5, 'Luis', 'Sanchez', 1900.00, 'Avenida Principal 234', 'Tarde', 2);
CALL sp_ListarEmpleado();
CALL sp_ActualizarEmpleado(1, 'Pedro', 'Lopez', 1800.00, 'Calle Central 456', 'Noche', 2);
CALL sp_ActualizarEmpleado(3, 'Juan', 'Garcia', 2200.00, 'Plaza Principal 789', 'Mañana', 3);
CALL sp_ActualizarEmpleado(5, 'Luisa', 'Fernandez', 2000.00, 'Avenida Central 234', 'Tarde', 1);
-- CALL sp_EliminarEmpleado(2)

CALL sp_AgregarFactura(1, 'Pendiente', 150.00, '2024-05-15', 1, 1);
CALL sp_AgregarFactura(2, 'Pagada', 200.00, '2024-05-16', 2, 2);
CALL sp_AgregarFactura(3, 'Pendiente', 180.00, '2024-05-17', 3, 1);
CALL sp_AgregarFactura(4, 'Pagada', 250.00, '2024-05-18', 1, 3);
CALL sp_AgregarFactura(5, 'Pendiente', 300.00, '2024-05-19', 2, 2);
CALL sp_ListarFactura();
CALL sp_ActualizarFactura(1, 'Pagada', 180.00, '2024-05-15', 2, 1);
CALL sp_ActualizarFactura(3, 'Pagada', 200.00, '2024-05-17', 3, 3);
CALL sp_ActualizarFactura(5, 'Pagada', 320.00, '2024-05-19', 1, 2);
-- CALL sp_EliminarFactura(2);
