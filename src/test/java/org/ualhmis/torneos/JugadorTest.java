package org.ualhmis.torneos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

// Restricciones en los equipos (jugadores de la misma categoría y modalidad)

class JugadorTest {

	@ParameterizedTest
	@CsvSource
	(
			{
				"'Carlos', 'Masculino', '2015-05-10', 'Infantil'",
				"'Luis', 'Masculino', '2010-03-15', 'Cadete'",
				"'Ana', 'Femenino', '2005-08-22', 'Juvenil'",
				"'Pedro', 'Masculino', '2002-01-30', 'Junior'",
				"'Marta', 'Femenino', '1998-06-05', 'Absoluta'"
			}
	)
    void testCategoriaPorEdad(String nombre, String genero, LocalDate fechaNacimiento, String categoriaEsperada) {
        Jugador jugador = new Jugador(nombre, genero, fechaNacimiento);
        assertEquals(categoriaEsperada, jugador.getCategoria());

    }

	@ParameterizedTest
	@CsvSource
	(
			{
				"'', 'Masculino', 2010-01-01",
				"'Juan', '', 2010-01-01",
				"'Juan', 'Masculino', ''"
			}
	)
    void testCreacionJugadorInvalido(String nombre, String genero, String fechaNacimiento) {
		LocalDate fechaNac = fechaNacimiento.equals("") ? null : LocalDate.parse(fechaNacimiento);
		assertThrows(IllegalArgumentException.class, () -> new Jugador(nombre, genero, fechaNac));
    }
}
