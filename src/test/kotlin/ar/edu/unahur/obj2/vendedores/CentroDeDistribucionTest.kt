package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe


class CentroDeDistribucionTest: DescribeSpec({
    val misiones = Provincia(1300000)
    val cordoba = Provincia(5000000)
    val entroRios = Provincia(1000000)
    val sanIgnacio = Ciudad(misiones)
    val obera = Ciudad(misiones)
    val parana = Ciudad(entroRios)
    val viajante = Viajante(listOf(misiones, cordoba))
    val vendedorFijo = VendedorFijo(obera)
    val centro = CentroDeDistribucion(ciudad = sanIgnacio, vendedores = mutableListOf(vendedorFijo, viajante))
    val certificacion1 = Certificacion(true,20)
    val certificacion2 = Certificacion(false,12)


    describe("Centro de Distribucion"){
        viajante.agregarCertificacion(certificacion1)
        vendedorFijo.agregarCertificacion(certificacion2)
        val exception = shouldThrow<Exception> {
            centro.agregarVendedores(vendedorFijo)
        }

        it("Error de agregado"){
        exception.message?.startsWith("YA EXISTE VENDEDOR")
        }
        it("es Vendedor Estrella"){
            centro.vendedorEstrella().shouldBe(viajante)
        }
        it("No es Vendedor Estrella"){
            centro.vendedorEstrella().shouldNotBe(vendedorFijo)
        }
        it("puede Cubrir"){
            centro.puedeCubrir(obera).shouldBeTrue()
        }
        // tiene que ser de otra provincia
        it("no puede Cubrir"){
            centro.puedeCubrir(parana).shouldBeFalse()
        }
        it("vendedores genericos"){
            centro.vendedoresGenericos().shouldBe(listOf(vendedorFijo))
        }
        it("no son vendedores genericos"){
            centro.vendedoresGenericos().shouldNotBe(listOf(viajante))
        }
        it("no es firme"){
            centro.esRobusto().shouldBeFalse()
        }
    }

})
