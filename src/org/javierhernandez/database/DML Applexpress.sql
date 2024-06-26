use DBApplexpress;

-- Cliente
-- Agregar cliente
DELIMITER $$
CREATE PROCEDURE sp_AgregarCliente (IN IDC int, IN nitC varchar(10), IN nombreC varchar(50),
    IN apellidoC varchar(50), IN direccionC varchar(150), IN telefonoC varchar(8), IN correoC varchar(45))
BEGIN
    INSERT INTO Clientes (IDCliente, nitCliente, nombreCliente, apellidoCliente, direccionCliente, telefonoCliente, correoCliente)
    VALUES (IDC, nitC, nombreC, apellidoC, direccionC, telefonoC ,correoC);
END$$
DELIMITER ;

CALL sp_AgregarCliente(1, '123456789', 'Juan', 'Perez', 'Calle Falsa 123', '12345678', 'juan.perez@example.com');
CALL sp_AgregarCliente(2, '987654321', 'Maria', 'Gonzalez', 'Avenida Principal 456', '87654321', 'maria.gonzalez@example.com');
CALL sp_AgregarCliente(3, '555555555', 'Pedro', 'Lopez', 'Calle Central 789', '33333333', 'pedro.lopez@example.com');
CALL sp_AgregarCliente(4, '777777777', 'Ana', 'Martinez', 'Avenida Norte 246', '99999999', 'ana.martinez@example.com');
CALL sp_AgregarCliente(5, '444444444', 'Luis', 'Sanchez', 'Calle Sur 135', '22222222', 'luis.sanchez@example.com');

-- Listar clientes
DELIMITER $$
CREATE PROCEDURE sp_ListarClientes ()
BEGIN
    SELECT 
    Clientes.IDCliente,
    Clientes.nitCliente, 
    Clientes.nombreCliente, 
    Clientes.apellidoCliente, 
    Clientes.direccionCliente, 
    Clientes.telefonoCliente, 
    Clientes.correoCliente
    FROM Clientes;
END$$
DELIMITER ;

-- Actualizar cliente
DELIMITER $$
CREATE PROCEDURE sp_ActualizarCliente (IN IDC int, IN nitC varchar(10), IN nombreC varchar(50), 
    IN apellidoC varchar(50), IN direccionC varchar(150), IN telefonoC varchar(8), IN correoC varchar(45))
BEGIN
    UPDATE Clientes
    SET
        nitCliente = nitC,
        nombreCliente = nombreC,
        apellidoCliente = apellidoC,
        direccionCliente = direccionC,
        telefonoCliente = telefonoC,
        correoCliente = correoC
    WHERE
        IDCliente = IDC;
END$$
DELIMITER ;

-- Eliminar Cliente 
DELIMITER $$
CREATE PROCEDURE sp_EliminarCliente (IN IDC int)
BEGIN
    DELETE FROM Clientes WHERE IDCliente = IDC;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_BuscarClientes(IN IDC int)
BEGIN
    SELECT 
    Clientes.IDCliente,
    Clientes.nitCliente, 
    Clientes.nombreCliente, 
    Clientes.apellidoCliente, 
    Clientes.direccionCliente, 
    Clientes.telefonoCliente, 
    Clientes.correoCliente 
    FROM Clientes 
    WHERE IDCliente = IDC;
END$$
DELIMITER ;

-- Tipo Producto
-- Agregar Tipo Producto
DELIMITER $$
CREATE PROCEDURE sp_AgregarTipoProducto (IN IdTP int, IN descr varchar(100))
BEGIN
    INSERT INTO TipoProducto (idTipoProducto, descripcion)
    VALUES (IdTP, descr);
END$$
DELIMITER ; 

CALL sp_AgregarTipoProducto(1, 'Frutas');
CALL sp_AgregarTipoProducto(2, 'Verduras');
CALL sp_AgregarTipoProducto(3, 'Productos de limpieza');
CALL sp_AgregarTipoProducto(4, 'Carnes y pescados');
CALL sp_AgregarTipoProducto(5, 'Productos lácteos');

-- Listar Tipo Producto
DELIMITER $$
CREATE PROCEDURE sp_ListarTipoProducto ()
BEGIN
    SELECT 
    TipoProducto.idTipoProducto,
    TipoProducto.descripcion
    FROM TipoProducto;
END$$
DELIMITER ;

-- Buscar Tipo Producto
DELIMITER $$
CREATE PROCEDURE sp_BuscarTipoProducto(IN IdTP int)
BEGIN
    SELECT 
    TipoProducto.idTipoProducto,
    TipoProducto.descripcion
    FROM TipoProducto 
    WHERE TipoProducto.idTipoProducto = IdTP;
END$$
DELIMITER ;

-- Actualizar Tipo Producto
DELIMITER $$
CREATE PROCEDURE sp_ActualizarTipoProducto (IN IdTP int, IN descr varchar(100))
BEGIN
    UPDATE TipoProducto
    SET
        TipoProducto.descripcion = descr
    WHERE
        TipoProducto.idTipoProducto = IdTP;
END$$

-- Eliminar Tipo Producto
DELIMITER $$
CREATE PROCEDURE sp_EliminarTipoProducto (IN IDTiProductos int)
BEGIN
	DELETE FROM Productos WHERE Productos.tipoProducto=IDTiProductos;
    DELETE FROM TipoProducto WHERE idTipoProducto = IDTiProductos;
END$$
DELIMITER ;


-- Compras
-- Agregar Compras
DELIMITER $$
CREATE PROCEDURE sp_AgregarCompras (IN IDCom int, IN fechaDoc date, IN descr varchar(60))
BEGIN
    INSERT INTO Compras (IDCompra, fechaDocumento, descripcion)
    VALUES (IDCom, fechaDoc, descr);
END$$
DELIMITER ;

CALL sp_AgregarCompras(1, '2024-06-16', 'Compra de productos de limpieza');
CALL sp_AgregarCompras(2, '2024-06-15', 'Compra de frutas y verduras frescas');
CALL sp_AgregarCompras(3, '2024-06-14', 'Compra de carne y pescado para la semana');
CALL sp_AgregarCompras(4, '2024-06-13', 'Compra de productos lácteos y huevos');
CALL sp_AgregarCompras(5, '2024-06-12', 'Compra de productos enlatados y no perecederos');


-- Listar Compras
DELIMITER $$
CREATE PROCEDURE sp_ListarCompras ()
BEGIN
    SELECT 
    Compras.IDCompra, 
    Compras.fechaDocumento, 
    Compras.descripcion, 
    Compras.totalDocumento
    FROM Compras;
END$$
DELIMITER ;

-- Buscar Compras
DELIMITER $$
CREATE PROCEDURE sp_buscarCompras(IN IDCom int)
BEGIN 
	SELECT
    Compras.IDCompra,
    Compras.fechaDocumento, 
    Compras.descripcion, 
    Compras.totalDocumento
    FROM Compras
    where Compras.IDCompra=IDCom;
END$$
DELIMITER;

-- Actualizar Compras
DELIMITER $$
CREATE PROCEDURE sp_ActualizarCompra (IN IDCom int, IN fechaDoc date, IN descr varchar(60))
BEGIN
    UPDATE Compras
    SET
        Compras.fechaDocumento = fechaDoc,
        Compras.descripcion = descr
    WHERE
        Compras.IDCompra = IDCom;
END$$
DELIMITER ;

-- Eliminar Compra 
DELIMITER $$
CREATE PROCEDURE sp_EliminarCompra (IN IDCom int)
BEGIN
    DELETE FROM DetalleCompra WHERE DetalleCompra.IDDetalleCompra=IDCom;
	DELETE FROM Compras WHERE Compras.IDCompra=IDCom;
END$$
DELIMITER ;

-- insertar total compras
delimiter $$
create procedure sp_actualizarComprasTotal(in IDCom int,in total decimal(10,2))
begin
	update Compras 
	set 
		Compras.totalDocumento=total
    where
		Compras.IDCompra=IDCom;
end $$
delimiter ;


-- Cargo Empleado
-- Agregar CargoEmpleado
DELIMITER $$
CREATE PROCEDURE sp_AgregarCargoEmpleado (IN IdCE int, IN nombre varchar(100), IN descr varchar(100))
BEGIN
    INSERT INTO CargoEmpleado (idCargoEmpleado, nombreCargo, descripcionCargo)
    VALUES (IdCE, nombre, descr);
END$$
DELIMITER ;

CALL sp_AgregarCargoEmpleado(1, 'Gerente', 'Encargado de la gestión general del negocio');
CALL sp_AgregarCargoEmpleado(2, 'Cajero', 'Responsable de las transacciones y pagos en caja');
CALL sp_AgregarCargoEmpleado(3, 'Reponedor', 'Encargado de reponer productos en los estantes');
CALL sp_AgregarCargoEmpleado(4, 'Vendedor', 'Encargado de atender a los clientes y promover ventas');
CALL sp_AgregarCargoEmpleado(5, 'Auxiliar de limpieza', 'Responsable de mantener la limpieza y orden del establecimiento');


-- Listar CargoEmpleado
DELIMITER $$
CREATE PROCEDURE sp_ListarCargoEmpleado ()
BEGIN
    SELECT 
    CargoEmpleado.idCargoEmpleado, 
    CargoEmpleado.nombreCargo, 
    CargoEmpleado.descripcionCargo
    FROM CargoEmpleado;
END$$
DELIMITER ;

-- Buscar Cargo Empleado
DELIMITER $$
CREATE PROCEDURE sp_BuscarCargoEmpleado(IN IdCE int)
BEGIN
    SELECT 
    CargoEmpleado.idCargoEmpleado, 
    CargoEmpleado.nombreCargo, 
    CargoEmpleado.descripcionCargo
    FROM CargoEmpleado 
    WHERE CargoEmpleado.idCargoEmpleado = IdCE;
END$$
DELIMITER ;

-- Actualizar CargoEmpleado
DELIMITER $$
CREATE PROCEDURE sp_ActualizaRCargoEmpleado (IN IdCE int, IN nombre varchar(100), IN descr varchar(100))
BEGIN
    UPDATE CargoEmpleado
    SET
        CargoEmpleado.nombreCargo = nombre, 
        CargoEmpleado.descripcionCargo = descr
    WHERE
        idCargoEmpleado = IdCE;
END$$

-- Eliminar CargoEmpleado
DELIMITER $$
CREATE PROCEDURE sp_EliminarCargoEmpleado (IN IdCE int)
BEGIN
    DELETE FROM CargoEmpleado WHERE CargoEmpleado.idCargoEmpleado = IdCE;
END$$
DELIMITER ;

-- Proveedor
-- Agregar Proveedor 	
DELIMITER $$
CREATE PROCEDURE sp_AgregarProveedor (IN IDPro int, IN nitPro varchar(10), IN nombrePro varchar(50), IN apellidoPro varchar(50), 
IN direccionPro varchar(150), IN razon varchar(60), IN contactoPrin varchar(100), IN pagWeb varchar(50))
BEGIN
    INSERT INTO Proveedores (IDProveedores, nitProveedor, nombreProveedor, apellidoProveedor, direccionProveedor, razonSocial, contactoPrincipal, paginaWeb)
    VALUES (IDPro, nitPro, nombrePro, apellidoPro, direccionPro, razon, contactoPrin, pagWeb);
END$$
DELIMITER ;

CALL sp_AgregarProveedor(1, '1234567890', 'Lima & Álvarez', 'Alias1', 'Calle Falsa 123', 'Lima & Álvarez S.A. de C.V.', 'Javier', 'www.limayalvarez.com');
CALL sp_AgregarProveedor(2, '0987654321', 'Juguetes Vikingos', 'Alias2', 'Avenida Siempre Viva 456', 'Juguetes Vikingos S.A.', 'Maria', 'www.juguetesvikingos.com');
CALL sp_AgregarProveedor(3, '1122334455', 'García & Asociados', 'Alias3', 'Boulevard de los Sueños 789', 'García & Asociados S.L.', 'Pablo', 'www.garciayasociados.com');
CALL sp_AgregarProveedor(4, '3344556677', 'Restaurante Chilangolandia', 'Alias4', 'Calle Luna 101', 'Restaurante Chilangolandia Ltd.', 'Oscar', 'www.chilangolandia.com');
CALL sp_AgregarProveedor(5, '2233445566', 'Pérez & Hijos', 'Alias5', 'Avenida Sol 202', 'Pérez & Hijos Inc.', 'Melany', 'www.perezhehijos.com');
CALL sp_AgregarProveedor(6, '4455667788', 'Tech Solutions', 'Alias6', 'Calle Estrella 303', 'Tech Solutions Corp.', 'Angel', 'www.techsolutions.com');
CALL sp_AgregarProveedor(7, '5566778899', 'Martínez y Compañía', 'Alias7', 'Avenida Cometa 404', 'Martínez y Compañía S.A.', 'Fatima', 'www.martinezycia.com');


-- Listar Proveedor
DELIMITER $$
CREATE PROCEDURE sp_ListarProveedores ()
BEGIN
    SELECT
    Proveedores.IDProveedores,
    Proveedores.nitProveedor,
    Proveedores.nombreProveedor,
    Proveedores.apellidoProveedor,
    Proveedores.direccionProveedor,
    Proveedores.razonSocial,
    Proveedores.contactoPrincipal,
    Proveedores.paginaWeb
    FROM Proveedores;
END$$
DELIMITER ;

-- Actualizar Proveedor
DELIMITER $$
CREATE PROCEDURE sp_ActualizarProveedor (IN IDPro int, IN nitPro varchar(10), IN nombrePro varchar(50), IN apellidoPro varchar(50), 
IN direccionPro varchar(150), IN razon varchar(60), IN contactoPrin varchar(100), IN pagWeb varchar(50))
BEGIN
    UPDATE Proveedores
    SET
        Proveedores.nitProveedor = nitPro,
        Proveedores.nombreProveedor = nombrePro,
        Proveedores.apellidoProveedor = apellidoPro,
        Proveedores.direccionProveedor = direccionPro,
        Proveedores.razonSocial = razon,
        Proveedores.contactoPrincipal = contactoPrin,
        Proveedores.paginaWeb = pagWeb
    WHERE
        Proveedores.IDProveedores = IDPro;
END$$
DELIMITER ;

-- Eliminar Proveedor 
DELIMITER $$
CREATE PROCEDURE sp_EliminarProveedor (IN IDPro int)
BEGIN
    DELETE FROM Productos  WHERE Productos.Proveedores = IDPro;
    DELETE FROM Proveedores WHERE Proveedores.IDProveedores = IDPro;
END$$
DELIMITER ;

-- Buscar Proveedor
DELIMITER $$
CREATE PROCEDURE sp_BuscarProveedor(IN IDPro int)
BEGIN
    SELECT 
    Proveedores.IDProveedores,
    Proveedores.nitProveedor,
    Proveedores.nombreProveedor,
    Proveedores.apellidoProveedor,
    Proveedores.direccionProveedor,
    Proveedores.razonSocial,
    Proveedores.contactoPrincipal,
    Proveedores.paginaWeb
    FROM Proveedores
    WHERE Proveedores.IDProveedores = IDPro;
END$$
DELIMITER ;

-- Productos
-- Agregar Producto
DELIMITER $$
CREATE PROCEDURE sp_AgregarProducto(IN IDProd INT, IN descrProducto VARCHAR(40), IN IdTp INT, IN IDPro INT)
BEGIN
    INSERT INTO Productos (IDProducto, descripcionProducto, idTipoProducto, IDProveedores)
    VALUES (IDProd, descrProducto, IdTp, IDPro);
END $$
DELIMITER ;

CALL sp_AgregarProducto(1, 'Manzanas Granny Smith', 1, 1);
CALL sp_AgregarProducto(2, 'Lechuga Iceberg', 2, 2);
CALL sp_AgregarProducto(3, 'Detergente líquido', 3, 3);
CALL sp_AgregarProducto(4, 'Filete de salmón', 4, 4);
CALL sp_AgregarProducto(5, 'Yogur natural', 5, 5);


-- Listar Producto
DELIMITER $$
CREATE PROCEDURE sp_ListarProducto()
BEGIN
    SELECT 
    Productos.IDProducto, 
    Productos.descripcionProducto, 
    Productos.precioUnitario, 
    Productos.precioDocena, 
    Productos.precioMayor, 
    Productos.existencia, 
    Productos.idTipoProducto, 
    Productos.IDProveedores
    FROM Productos;
END $$
DELIMITER ;

-- Buscar Productos
DELIMITER $$
CREATE PROCEDURE sp_BuscarProducto(IN IDProd INT)
BEGIN
    SELECT 
    Productos.IDProducto, 
    Productos.descripcionProducto, 
    Productos.precioUnitario, 
    Productos.precioDocena, 
    Productos.precioMayor, 
    Productos.existencia, 
    Productos.idTipoProducto, 
    Productos.IDProveedores
    FROM Productos
    WHERE Productos.IDProducto=IDProd;
END $$
DELIMITER ;

-- Actualizar Producto
DELIMITER &&
CREATE PROCEDURE sp_ActualizarProducto(IN IDProd INT, IN descrProducto VARCHAR(40), IN IdTp INT, IN IDPro INT)
BEGIN
    UPDATE Productos
    SET 
    Productos.descripcionProducto = descrProducto, 
	Productos.idTipoProducto = IdTp,
	Productos.IDProveedores = IDPro
    WHERE 
    Productos.IDProducto = IDProd;
END &&
DELIMITER ;

-- Actualizar Precios Productos
DELIMITER $$
CREATE PROCEDURE sp_ActualizarPreciosProductos(in IDProd INT,in precUnit decimal(10,2),in precDoc decimal(10,5), in precMay decimal(10,2))
BEGIN
	UPDATE Productos 
	SET 
		Productos.precioUnitario=precUnit,
		Productos.precioDocena=precDoc,
        Productos.precioMayor=precMay
	WHERE
		Productos.IDProducto=IDProd;
END $$
DELIMITER ;

-- Eliminar Producto 
DELIMITER && 
CREATE PROCEDURE sp_eliminarProducto(IN IDProd INT) 
BEGIN 
	DELETE FROM DetalleFactura WHERE DetalleFactura.IDProducto = IDProd; 
    DELETE FROM DetalleCompra WHERE DetalleCompra.IDProducto = IDProd; 
    DELETE FROM Productos WHERE Productos.IDProducto = IDProd; 
END && 
DELIMITER ;



----------------------------------------- Detalle Compra
-- Agregar Detalle Compra
DELIMITER $$
CREATE PROCEDURE sp_AgregarDetalleCompra(IN IdDC INT, IN Uni DECIMAL(10,2), IN cant INT, IN IDPro INT, IN IDComp INT)
BEGIN
	INSERT INTO DetalleCompra(IDDetalleCompra,costoUnitario,cantidad,IDProducto,IDCompra)
		VALUES(IdDC, Uni, cant, IDPro, IDComp);
END$$
DELIMITER ;

CALL sp_AgregarDetalleCompra(1, 2.50, 5, 1, 1);
CALL sp_AgregarDetalleCompra(2, 1.75, 3, 2, 1);
CALL sp_AgregarDetalleCompra(3, 3.00, 2, 3, 2);
CALL sp_AgregarDetalleCompra(4, 8.50, 1, 4, 3);
CALL sp_AgregarDetalleCompra(5, 0.75, 10, 5, 4);



-- Listar Detalle Compra
DELIMITER $$
CREATE PROCEDURE sp_ListarDetalleCompra()
BEGIN
	SELECT 
    DetalleCompra.IDDetalleCompra,
    DetalleCompra.costoUnitario,
    DetalleCompra.cantidad,
    DetalleCompra.IDProducto,
    DetalleCompra.IDCompra
    FROM DetalleCompra;
END$$
DELIMITER ;

-- Buscar Detalle Compra
DELIMITER $$
CREATE PROCEDURE sp_BuscarDetalleCompra(IN IdDC INT)
BEGIN
	SELECT 
    DetalleCompra.IDDetalleCompra,
    DetalleCompra.costoUnitario,
    DetalleCompra.cantidad,
    DetalleCompra.IDProducto,
    DetalleCompra.IDCompra
    FROM DetalleCompra
    WHERE DetalleCompra.IDDetalleCompra=IdDC;
END$$
DELIMITER ;

-- Actualizar Detalle Compra
DELIMITER $$
CREATE PROCEDURE sp_ActualizarDetalleCompra(IN IdDC INT, IN Uni DECIMAL(10,2), IN cant INT, IN IDPro INT, IN IDComp INT)
BEGIN
	UPDATE DetalleCompra
    SET 
    DetalleCompra.costoUnitario = Uni, 
    DetalleCompra.cantidad = cant, 
    DetalleCompra.IDProducto = IDPro, 
    DetalleCompra.IDCompra = IDComp
    
    WHERE DetalleCompra.IDDetalleCompra = IdDC;
END$$
DELIMITER ;

-- Eliminar Detalle Compra
DELIMITER $$
CREATE PROCEDURE sp_EliminarDetalleCompra(IN IDProd INT)
BEGIN
	DELETE FROM DetalleCompra WHERE DetalleCompra.IDDetalleCompra = IDProd;
END$$
DELIMITER ;

-- Empleado
-- Agregar Empleado
DELIMITER $$
CREATE PROCEDURE sp_AgregarEmpleado(IN IDEmp INT, IN nomEmpleado VARCHAR(50), IN apeEmpleado VARCHAR(50),
IN sueldo DECIMAL(10,2), IN dir VARCHAR(150), IN turn VARCHAR(15), IN IdCG INT)
BEGIN
    INSERT INTO Empleados (IDEmpleado, nombresEmpleado, apellidosEmpleado, sueldo, direccion, turno, idCargoEmpleado)
    VALUES (IDEmp, nomEmpleado, apeEmpleado, sueldo, dir, turn, IdCG);
END $$
DELIMITER ;

CALL sp_AgregarEmpleado(1, 'Juan', 'Perez', 1500.00, 'Calle Principal 123', 'Matutino', 1);
CALL sp_AgregarEmpleado(2, 'Maria', 'Gomez', 1800.00, 'Avenida Central 456', 'Vespertino', 2);
CALL sp_AgregarEmpleado(3, 'Carlos', 'Lopez', 1600.00, 'Calle Secundaria 789', 'Nocturno', 3);
CALL sp_AgregarEmpleado(4, 'Ana', 'Martinez', 1700.00, 'Calle Central 246', 'Matutino', 2);
CALL sp_AgregarEmpleado(5, 'Pedro', 'Rodriguez', 1900.00, 'Avenida Principal 789', 'Vespertino', 1);


-- Listar Empleado 
DELIMITER &&
CREATE PROCEDURE sp_ListarEmpleado()
BEGIN
    SELECT 
    Empleados.IDEmpleado, 
    Empleados.nombresEmpleado, 
    Empleados.apellidosEmpleado, 
    Empleados.sueldo, 
    Empleados.direccion, 
    Empleados.turno, 
    Empleados.idCargoEmpleado
    FROM Empleados;
END &&
DELIMITER ;

-- Buscar Empleados
DELIMITER $$
CREATE PROCEDURE sp_BuscarEmpleados (IN IDEmp int)
BEGIN
    SELECT 
    Empleados.IDEmpleado, 
    Empleados.nombresEmpleado, 
    Empleados.apellidosEmpleado, 
    Empleados.sueldo, 
    Empleados.direccion, 
    Empleados.turno, 
    Empleados.idCargoEmpleado
    FROM Empleados 
    WHERE Empleados.IDEmpleado = IDEmp;
END$$
DELIMITER ;

-- Actualizar Empleado
DELIMITER $$
CREATE PROCEDURE sp_ActualizarEmpleado(IN IDEmp INT, IN nomEmpleado VARCHAR(50), IN apeEmpleado VARCHAR(50),
IN sueldo DECIMAL(10,2), IN dir VARCHAR(150), IN turn VARCHAR(15), IN IdCG INT)
BEGIN
    UPDATE Empleados
    SET 
    Empleados.nombresEmpleado = nomEmpleado, 
	Empleados.apellidosEmpleado = apeEmpleado, 
	Empleados.sueldo = sueldo, 
	Empleados.direccion = dir,
	Empleados.turno = turn,
	Empleados.idCargoEmpleado = IdCG
    WHERE Empleados.IDEmpleado = IDEmp;
END $$
DELIMITER ;

-- Eliminar Empleado
DELIMITER $$
CREATE PROCEDURE sp_EliminarEmpleado(IN IDEmp INT)
BEGIN
    DELETE FROM Empleados WHERE Empleados.IDEmpleado = IDEmp;
END $$
DELIMITER ;

-- Factura
-- Agregar Factura
DELIMITER $$
CREATE PROCEDURE sp_AgregarFactura(IN IdF INT, IN est VARCHAR(50), IN fecha VARCHAR(45), IN IdC INT, IN IdEmpl INT)
BEGIN
    INSERT INTO Factura (IDDeFactura, estado,  fechaFactura, IDCliente, IDEmpleado)
    VALUES (IdF, est, fecha, IdC, IdEmpl);
END $$
DELIMITER ;

CALL sp_AgregarFactura(1, 'Pagado', '2024-06-10', 1, 1);
CALL sp_AgregarFactura(2, 'Pendiente', '2024-06-11', 2, 2);
CALL sp_AgregarFactura(3, 'Pagado', '2024-06-12', 3, 3);
CALL sp_AgregarFactura(4, 'Pendiente', '2024-06-13', 4, 1);
CALL sp_AgregarFactura(5, 'Pagado', '2024-06-14', 5, 2);

-- Listar Factura
DELIMITER $$
CREATE PROCEDURE sp_ListarFactura()
BEGIN
    SELECT 
    Factura.IDDeFactura, 
    Factura.estado, 
    Factura.totalFactura, 
    Factura.fechaFactura, 
    Factura.IDCliente, 
    Factura.IDEmpleado
    FROM Factura;
END $$
DELIMITER ;

-- Buscar Factura
DELIMITER $$
CREATE PROCEDURE sp_BuscarFactura(IN IdF INT)
BEGIN
    SELECT 
    Factura.IDDeFactura, 
    Factura.estado, 
    Factura.totalFactura, 
    Factura.fechaFactura, 
    Factura.IDCliente, 
    Factura.IDEmpleado
    FROM Factura
    WHERE Factura.IDDeFactura=IdF;
END $$
DELIMITER ;

-- Actualizar Factura
DELIMITER $$
CREATE PROCEDURE sp_ActualizarFactura(IN IdF INT, IN est VARCHAR(50), IN total DECIMAL(10,2), 
IN fecha VARCHAR(45), IN IdC INT, IN IdEmpl INT)
BEGIN
    UPDATE Factura
    SET 
    Factura.estado = est, 
        Factura.totalFactura = total,
        Factura.fechaFactura = fecha,
        Factura.IDCliente = IdC,
        Factura.IDEmpleado = IdEmpl
    WHERE Factura.IDDeFactura = IdF;
END $$
DELIMITER ;

-- insertar total factura
DELIMITER $$
CREATE PROCEDURE sp_actualizarFacturaTotal(IN IdF INT,IN total DECIMAL(10,2))
BEGIN
	UPDATE Factura 
	SET 
		Factura.totalFactura=total
    WHERE
		Factura.IDDeFactura=IdF;
END $$
DELIMITER ;

-- Elimianr Factura
DELIMITER $$
CREATE PROCEDURE sp_EliminarFactura(IN IdF INT)
BEGIN
    DELETE FROM Factura WHERE Factura.IDDeFactura = IdF;
END $$
DELIMITER ;

-- Detalle Fatura 
-- Agregar Detalle Factura 
DELIMITER $$
CREATE PROCEDURE sp_AgregarDetalleFactura(IN IdDF INT, IN cant INT, IN IdF INT, IN IdProd INT)
BEGIN
    INSERT INTO DetalleFactura (IDDetalleFactura, cantidad, IDDeFactura, IDProducto)
    VALUES (IdDF, cant, IdF, IDProd);
END $$
DELIMITER ;

CALL sp_AgregarDetalleFactura(1, 3, 1, 1);
CALL sp_AgregarDetalleFactura(2, 2, 1, 2);
CALL sp_AgregarDetalleFactura(3, 1, 2, 3);
CALL sp_AgregarDetalleFactura(4, 4, 3, 4);
CALL sp_AgregarDetalleFactura(5, 5, 4, 5);

-- Listar Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_ListarDetalleFactura()
BEGIN
    SELECT 
    DetalleFactura.IDDetalleFactura, 
    DetalleFactura.precioUnitario, 
    DetalleFactura.cantidad, 
    DetalleFactura.IDDeFactura, 
    DetalleFactura.IDProducto
    FROM DetalleFactura;
END $$
DELIMITER ;

-- Buscar Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_BuscarDetalleFactura(IN IdDF INT)
BEGIN
    SELECT 
    DetalleFactura.IDDetalleFactura, 
    DetalleFactura.precioUnitario, 
    DetalleFactura.cantidad, 
    DetalleFactura.IDDeFactura, 
    DetalleFactura.IDProducto
    FROM DetalleFactura
    WHERE DetalleFactura.IDDetalleFactura=IdDF;
END $$
DELIMITER ;

-- Actualizar Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_ActualizarDetalleFactura(IN IdDF INT, IN Unit DECIMAL(10,2),IN cant INT, IN IdF INT, IN IDProd INT)
BEGIN
    UPDATE DetalleFactura
    SET 
    DetalleFactura.precioUnitario = Unit, 
	DetalleFactura.cantidad = cant,
	DetalleFactura.IDDeFactura = IdF,
	DetalleFactura.IDProducto = IDProd
    WHERE DetalleFactura.IDDetalleFactura = IdDF;
END $$
DELIMITER ;

-- Elimianr Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_EliminarDetalleFactura(IN IdDF INT)
BEGIN
    DELETE FROM DetalleFactura WHERE DetalleFactura.IDDetalleFactura = IdDF;
END $$
DELIMITER ;

-- traer el precio unitario
delimiter //
create function fn_TraerPrecioUnitario(IDProd int) returns decimal(10,2)
deterministic
begin
	declare precio decimal(10,2);
	set precio= (select DetalleCompra.costoUnitario from DetalleCompra
    where DetalleCompra.IDProducto=IDProd limit 1);
	return precio;
end //

delimiter ;

-- Precios Detalle factura
-- insertar Precios Detalle factura
delimiter //
create trigger tr_insertarPreciosDetalleFactura_Before_Insert
before insert on DetalleFactura
for each row
	begin
        set new.precioUnitario= (select precioUnitario from Productos
		where Productos.IDProducto=new.IDProducto limit 1);
        
	end //
delimiter ;

-- actualizar DetalleFactura
delimiter $$
create procedure sp_actualizarPrecioDetalleFactura(in IDProd varchar(15), in precUnit decimal(10,2) )
begin
	update DetalleFactura 
	set 
		DetalleFactura.precioUnitario=precUnit
    where
		DetalleFactura.IDProducto=IDProd;
end $$
delimiter ;


-- actualizar Precios Detalle factura
delimiter //
create trigger tr_actualizarPreciosDetalleFactura_after_update
after update on Productos
for each row
	begin
		call sp_actualizarPrecioDetalleFactura(new.IDProducto,
        (select new.precioUnitario from Productos where Productos.IDProducto=new.IDProducto limit 1));
        
	end //
delimiter ;


-- insertar precios en Productos
delimiter //
create trigger tr_insertarPreciosProductos_after_Insert
after insert on DetalleCompra
for each row
	begin
    call sp_actualizarPreciosProductos(new.IDProducto, 
									(fn_TraerPrecioUnitario(new.IDProducto)+(fn_TraerPrecioUnitario(new.IDProducto)*0.40)),
									(fn_TraerPrecioUnitario(new.IDProducto)+(fn_TraerPrecioUnitario(new.IDProducto)*0.35)),
                                    (fn_TraerPrecioUnitario(new.IDProducto)+(fn_TraerPrecioUnitario(new.IDProducto)*0.25)));
                                    
	end //
delimiter ;


-- actualizar precios en Productos
delimiter //
create trigger tr_actualizarPreciosProductos_after_update
after update on DetalleCompra
for each row
	begin
    call sp_actualizarPreciosProductos(new.IDProducto, 
									(fn_TraerPrecioUnitario(new.IDProducto)+(fn_TraerPrecioUnitario(new.IDProducto)*0.40)),
									(fn_TraerPrecioUnitario(new.IDProducto)+(fn_TraerPrecioUnitario(new.IDProducto)*0.35)),
                                    (fn_TraerPrecioUnitario(new.IDProducto)+(fn_TraerPrecioUnitario(new.IDProducto)*0.25)));
                                    
	end //
delimiter ;


-- eliminar precios en Productos
delimiter //
create trigger tr_eliminarPreciosProductos_after_delete
after delete on DetalleCompra
for each row
	begin
    call sp_actualizarPreciosProductos(old.IDProducto, 0,0,0);
                                    
	end //
delimiter ;


-- insertar total compra
delimiter //
create trigger tr_insertarTotalCompra_after_Insert
after insert on DetalleCompra
for each row
	begin
    declare total decimal(10,2);
    
    set total=((select sum(costoUnitario*cantidad) from DetalleCompra where DetalleCompra.IDCompra=new.IDCompra ));
    
    call sp_actualizarComprasTotal(new.IDCompra, total);
                                    
	end //
delimiter ;


-- actualizar total compra
delimiter //
create trigger tr_actualizarTotalCompra_after_update
after update on DetalleCompra
for each row
	begin
    declare total decimal(10,2);
    
    set total=((select sum(new.costoUnitario*new.cantidad) from DetalleCompra where DetalleCompra.IDCompra=new.IDCompra ));
    
    call sp_actualizarComprasTotal(new.IDCompra, total);
                                    
	end //
delimiter ;

-- total compra
delimiter //
create function fn_TotalCompra(IdFac int) returns decimal(10,2)
deterministic
begin
    declare sumatoria decimal(10,2);
    
    set sumatoria = (select sum(cantidad*costoUnitario) from DetalleCompra 
					where IDDeFactura=IdFac ) ;
    return sumatoria;
end //
delimiter ;

-- eliminar total compra
delimiter //
create trigger tr_eliminarTotalCompra_after_delete
after delete on DetalleCompra
for each row
	begin
    declare total decimal(10,2);
    
    set total=fn_TotalCompra(old.IDCompra);
    
    call sp_actualizarComprasTotal(old.IDCompra, total);
                                    
	end //
delimiter ;


-- insertar total factura
delimiter //
create trigger tr_insertarTotalFactura_after_Insert
after insert on DetalleFactura
for each row
	begin
    declare total decimal(10,2);
    
    set total=((select sum(precioUnitario*cantidad) from DetalleFactura where DetalleFactura.IDDeFactura=new.IDDeFactura ));
    
    call sp_actualizarFacturaTotal(new.IDDeFactura, total);
    
                                    
	end //
delimiter ;

-- actualizar total factura
delimiter //
create trigger tr_actualizarTotalFactura_after_update
after update on DetalleFactura
for each row
	begin
    declare total decimal(10,2);
    
    set total=((select sum(new.precioUnitario*cantidad) from DetalleFactura where DetalleFactura.IDDeFactura=new.IDDeFactura ));
    
    call sp_actualizarFacturaTotal(new.IDDeFactura, total);
                                    
	end //
delimiter ;


-- total factura
delimiter //
create function fn_TotalFactura(numFact int) returns decimal(10,2)
deterministic
begin
    declare sumatoria decimal(10,2);
    
    set sumatoria = (select sum(precioUnitario*cantidad) from DetalleFactura 
					where IDDeFactura=numFact) ;
    return sumatoria;
end //
delimiter ;

-- eliminar total factura
delimiter //
create trigger tr_eliminarTotalFactura_after_delete
after delete on DetalleFactura
for each row
	begin
    declare total decimal(10,2);
    
    set total=fn_TotalFactura(old.IDDeFactura);
    
    call sp_actualizarFacturaTotal(old.IDDeFactura, total);
                                    
	end //
delimiter ;


-- existencias
-- proceso almacenado
delimiter $$
create procedure sp_actualizarExistenciaProductos(in IDProd varchar(15), in exist int )
begin
	update Productos 
	set 
		Productos.existencia=exist
    where
		Productos.IDProducto=IDProd;
end $$
delimiter ;

-- traer el precio unitario
delimiter //
create function fn_TraerExistencias(IDProd int) returns int
deterministic
begin
	declare existencias int;
	set existencias= (select existencia from Productos where IDProducto=IDProd limit 1);
	return existencias;
end //

delimiter ;

delimiter //
create trigger tr_insertarExistenciasProductos_after_insert
before insert on Productos
for each row
	begin
		set new.existencia=0;          
	end //
delimiter ;

-- trigger
delimiter //
create trigger tr_insertarNuevasExistenciasProductos_after_insert
after insert on DetalleCompra
for each row
	begin
		declare cant int;
		
        set cant= (select existencia from Productos where Productos.IDProducto=new.IDProducto);
        
		update Productos
        set
			Productos.existencia=new.cantidad+cant,
            Productos.precioUnitario=new.costoUnitario
        where
            Productos.IDProducto=new.IDProducto;             
	end //
delimiter ;

CALL sp_ListarClientes();
CALL sp_ListarTipoProducto();
CALL sp_ListarCompras();
CALL sp_ListarCargoEmpleado();
CALL sp_ListarProveedores();
CALL sp_ListarProducto();
CALL sp_ListarDetalleCompra();
CALL sp_ListarEmpleado();
CALL sp_ListarFactura();
CALL sp_ListarDetalleFactura();

CALL sp_BuscarClientes(1);
CALL sp_BuscarTipoProducto(1);
CALL sp_buscarCompras(1);
CALL sp_BuscarCargoEmpleado(1);
CALL sp_BuscarProveedor(1);
CALL sp_BuscarProducto(1);
CALL sp_BuscarDetalleCompra(1);
CALL sp_BuscarEmpleados(1);
CALL sp_BuscarFactura(1);
CALL sp_BuscarDetalleFactura(1);

CALL sp_eliminarProducto(5);