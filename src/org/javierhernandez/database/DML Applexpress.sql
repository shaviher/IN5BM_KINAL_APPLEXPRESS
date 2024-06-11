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

-- Listar CargoEmpleado
DELIMITER $$
CREATE PROCEDURE sp_ListarCargoEmpleado ()
BEGIN
    SELECT 
    CargoEmpleadoidCargoEmpleado, 
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
    CargoEmpleadoidCargoEmpleado, 
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
CREATE PROCEDURE sp_eliminarproducto(IN IDProd INT)
BEGIN
	DELETE FROM DetalleCompra WHERE DetalleCompra.IDProducto=IDProd;
    DELETE FROM Productos WHERE Productos.IDProducto=IDProd;
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
CREATE PROCEDURE sp_EliminarDetalleCompra(IN IdDC INT)
BEGIN
	DELETE FROM DetalleCompra WHERE DetalleCompra.IDDetalleCompra = IdDC;
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
		Factura.numeroFactura=numFac;
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
CREATE PROCEDURE sp_AgregarDetalleFactura(IN IdDF INT, IN Unit DECIMAL(10,2),IN cant INT, IN IdF INT, IN IdPro INT)
BEGIN
    INSERT INTO DetalleFactura (IDDetalleFactura, precioUnitario, cantidad, IDDeFactura, IDProducto)
    VALUES (IdDF, Unit, cant, IdF, IdPro);
END $$
DELIMITER ;

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
CREATE PROCEDURE sp_ActualizarDetalleFactura(IN IdDF INT, IN Unit DECIMAL(10,2),IN cant INT, IN IdF INT, IN IdPro INT)
BEGIN
    UPDATE DetalleFactura
    SET 
    DetalleFactura.precioUnitario = Unit, 
	DetalleFactura.cantidad = cant,
	DetalleFactura.IDDeFactura = IdF,
	DetalleFactura.IDProducto = IdPro
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
DELIMITER $$
CREATE FUNCTION fn_TraerPrecioUnitario(IDProd INT) RETURNS DECIMAL(10,2)
deterministic
begin
	declare precio decimal(10,2);
	set precio= (select DetalleCompra.costoUnitario from DetalleCompra
    where DetalleCompra.IDProducto=IDProd);
	return precio;
end //
DELIMITER ;


-- Precios Detalle factura
-- insertar Precios Detalle factura
delimiter //
create trigger tr_insertarPreciosDetalleFactura_Before_Insert
before insert on DetalleFactura
for each row
	begin
        set new.precioUnitario= (select precioUnitario from Productos
		where Productos.IDProducto=new.IDProducto);
        
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
        (select new.precioUnitario from Productos where Productos.IDProducto=new.IDProducto));
        
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
    declare totalDocumento decimal(10,2);
    
    set totalDocumento=((select sum(costoUnitario*cantidad) from DetalleCompra where DetalleCompra.IDDetalleCompra=new.IDDetalleCompra));
    
    call sp_actualizarComprasTotal(new.IDDetalleCompra, totalDocumento);
                                    
	end //
delimiter ;

-- actualizar total compra
delimiter //
create trigger tr_actualizarTotalCompra_after_update
after update on DetalleCompra
for each row
	begin
    declare totalDocumento decimal(10,2);
    
    set totalDocumento=((select sum(new.costoUnitario*new.cantidad) from DetalleCompra where DetalleCompra.IDDetalleCompra=new.IDDetalleCompra));
    
    call sp_actualizarComprasTotal(new.IDDetalleCompra, totalDocumento);
                                    
	end //
delimiter ;

-- total compra
delimiter //
create function fn_TotalCompra(IDDetalleCompra int) returns decimal(10,2)
deterministic
begin
    declare sumatoria decimal(10,2);
    
    set sumatoria = (select sum(cantidad*costoUnitario) from DetalleCompra 
					where IDDetalleCompra=IDDetalleCompra) ;
    return sumatoria;
end //
delimiter ;

-- eliminar total compra
delimiter //
create trigger tr_eliminarTotalCompra_after_delete
after delete on DetalleCompra
for each row
	begin
    declare totalDocumento decimal(10,2);
    
    set totalDocumento=fn_TotalCompra(old.IDDetalleCompra);
    
    call sp_actualizarComprasTotal(old.IDDetalleCompra, totalDocumento);
                                    
	end //
delimiter ;


-- insertar total factura
delimiter //
create trigger tr_insertarTotalFactura_after_Insert
after insert on DetalleFactura
for each row
	begin
    declare totalDocumento decimal(10,2);
    
    set totalDocumento=((select sum(precioUnitario*cantidad) from DetalleFactura where DetalleFactura.IDDeFactura=new.IDDeFactura ));
    
    call sp_actualizarFacturaTotal(new.IDDeFactura, totalDocumento);
                                    
	end //
delimiter ;

-- actualizar total factura
delimiter //
create trigger tr_actualizarTotalFactura_after_update
after update on DetalleFactura
for each row
	begin
    declare totalDocumento decimal(10,2);
    
    set totalDocumento=((select sum(new.precioUnitario*cantidad) from DetalleFactura where DetalleFactura.IDDeFactura=new.IDDeFactura ));
    
    call sp_actualizarFacturaTotal(new.IDDeFactura, totalDocumento);
                                    
	end //
delimiter ;


-- total factura
delimiter //
create function fn_TotalFactura(IdF int) returns decimal(10,2)
deterministic
begin
    declare sumatoria decimal(10,2);
    
    set sumatoria = (select sum(precioUnitario*cantidad) from DetalleFactura 
					where IDDeFactura=IdF) ;
    return sumatoria;
end //
delimiter ;

-- eliminar total factura
delimiter //
create trigger tr_eliminarTotalFactura_after_delete
after delete on DetalleFactura
for each row
	begin
    declare totalDocumento decimal(10,2);
    
    set totalDocumento=fn_TotalFactura(old.IDDeFactura);
    
    call sp_actualizarFacturaTotal(old.IDDeFactura, totalDocumento);
                                    
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
create function fn_TraerExistencias(IDProd varchar(15)) returns int
deterministic
begin
	declare existencias int;
	set existencias= (select existencia from Productos where IDProducto=IDProd);
	return existencias;
end //

delimiter ;


-- trigger
delimiter //
create trigger tr_actualizarExistencias_before_insert
before insert on DetalleFactura
for each row
	begin
		declare exist int;
        declare cant int;
        
        set cant = new.cantidad;
		set exist= fn_TraerExistencias(new.IDProducto);
        
        if (exist > cant) then
        
			set exist=exist-cant;
			
			call sp_actualizarExistenciaProductos(new.IDProducto, exist);
            
		elseif (exist = cant) then
        
			set exist=exist-cant;
			
			call sp_actualizarExistenciaProductos(new.IDProducto, exist);
        
        else
			signal sqlstate "45000" set message_text = "supera el numero de existencias o no hay existencias";
        end if;
                           
	end //
delimiter ;


-- trigger
delimiter //
create trigger tr_actualizarCantidad_before_insert
after insert on DetalleCompra
for each row
	begin
		  declare cant int;
          
          set cant = (select existencia from Productos where IDProducto=new.IDProducto);
          
          call sp_actualizarExistenciaProductos(new.IDProducto, (cant+new.cantidad));
          
	end //
delimiter ;