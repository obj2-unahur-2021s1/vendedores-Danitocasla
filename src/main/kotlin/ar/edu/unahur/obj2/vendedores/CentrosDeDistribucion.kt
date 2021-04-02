package ar.edu.unahur.obj2.vendedores

class CentrosDeDistribucion(ciudad: Ciudad, private val vendedores: List<Vendedor>) {
    fun vendedorEstrella(): Vendedor? {
        return vendedores.maxBy { it.puntajeCertificaciones() }
    }
    fun puedeCubrir( unaCiudad: Ciudad) = vendedores.any { it.puedeTrabajarEn(ciudad = unaCiudad) }
    fun vendedoresGenericos() = vendedores.filter { it.esGenerico() }
    fun esRobusto() = (vendedores.filter{it.esFirme()}).size >= 3
}