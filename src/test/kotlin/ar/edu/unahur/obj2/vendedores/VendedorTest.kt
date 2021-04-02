package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue


class VendedorTest : DescribeSpec({
    val misiones = Provincia(1300000)
    val sanIgnacio = Ciudad(misiones)
    val cordoba = Provincia(2000000)

    describe("Vendedor fijo") {
        val obera = Ciudad(misiones)
        val vendedorFijo = VendedorFijo(obera)

        describe("puedeTrabajarEn") {
            it("su ciudad de origen") {
                vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
            }
            it("otra ciudad") {
                vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
            }
        }
        describe("esInfluyente"){
            it("es influyente"){
                vendedorFijo.esInfluyente().shouldBeFalse()
            }
        }
    }

    describe("Viajante") {

        val villaDolores = Ciudad(cordoba)
        val viajante = Viajante(listOf(misiones))

        describe("puedeTrabajarEn") {
            it("una ciudad que pertenece a una provincia habilitada") {
                viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
            }
            it("una ciudad que no pertenece a una provincia habilitada") {
                viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
            }
        }
        describe("esInfluyente"){
            it("es influyente") {
                viajante.esInfluente().shouldBeTrue()
            }
        }
    }

    describe("Comercio Corresponsal"){
        val buenosAires = Provincia(10000000)
        val santaFe = Provincia(2000000)
        val entreRios = Provincia(1000000)
        val chivilcoy = Ciudad(buenosAires)
        val bragado = Ciudad(buenosAires)
        val lobos = Ciudad(buenosAires)
        val pergamino = Ciudad(buenosAires)
        val zarate = Ciudad(buenosAires)
        val sanFrancisco = Ciudad(cordoba)
        val rosario = Ciudad(santaFe)
        val rafaela = Ciudad(santaFe)
        val amstrong = Ciudad(santaFe)
        val diamante = Ciudad(entreRios)
        val comercio1 = ComercioCorresponsal(listOf(chivilcoy, bragado, lobos, pergamino, zarate))
        val comercio2 = ComercioCorresponsal(listOf(rosario, rafaela, sanFrancisco, diamante))
        val comercio3 = ComercioCorresponsal(listOf(rosario, rafaela, amstrong))

        describe("puedeTrabajarEn") {
            it("una ciudad que pertenece a una provincia habilitada") {
                comercio1.puedeTrabajarEn(bragado)
            }
            it("una ciudad que no pertenece a una provincia habilitada") {
                comercio1.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
            }
            it("ciudad que pertenece a una provincia habilitada") {
                comercio2.puedeTrabajarEn(rafaela).shouldBeTrue()
            }
            it("ciudad que no pertenece a una provincia habilitada") {
                comercio2.puedeTrabajarEn(chivilcoy).shouldBeFalse()
            }
            it("la ciudad que pertenece a una provincia habilitada") {
                comercio3.puedeTrabajarEn(amstrong).shouldBeTrue()
            }
            it("la ciudad que no pertenece a una provincia habilitada") {
                comercio3.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
            }
        }
        describe("esInfluyente"){
            it("es influyente") {
                comercio1.esInfluyente().shouldBeTrue()
            }
            it("es influyente2") {
                comercio2.esInfluyente().shouldBeTrue()
            }
            it("no es influyente") {
                comercio3.esInfluyente().shouldBeFalse()
            }
        }
    }
})
