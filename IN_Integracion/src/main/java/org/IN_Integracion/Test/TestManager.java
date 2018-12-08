package org.IN_Integracion.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import org.I1_AperturaExpedientes.Persistencia.*;
import org.I1_AperturaExpedientes.Presentacion.Presentacion_VerActividad;
import org.I2_SancionConductor.Presentacion.*;
import org.I4_CambioPropietario.Presentacion.*;

import I3_PagoSancion.Presentacion.Presentacion_PagarSancion;

public class TestManager {
	
		
	@Test
	public void testEsInfraccion() throws Exception {
	    Propietario p = new Propietario(12345678);
		
		Vehiculo v = new Vehiculo('1425GVF',12345678);
		
		Radar r1 = new Radar(1, 100, 'Carretera Poblete km.4');
		Radar r2 = new Radar(2, 90, 'Carretera Porzuna km.1');
		Radar r3 = new Radar(3, 80, 'Carretera Toledo km.8');
		
		Foto f1 = new Foto('1425GVF', 0001, 140, '20181205_0745');
		Foto f2 = new Foto('1425GVF', 0002, 89, '20181205_0845');
		Foto f3 = new Foto('1425GVF', 0003, 75, '20181205_0945');
		
		assertTrue(f1.esInfraccion()==true);
		assertTrue(f2.esInfraccion()==false);
		assertTrue(f3.esInfraccion()==false);
			
		
     
	}

	
		
}


