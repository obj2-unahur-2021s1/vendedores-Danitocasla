package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion(ciudad: Ciudad) {
    private val vendedores = mutableListOf<Vendedor>()

    fun agregarVendedores(vendedor: Vendedor) {
        if(vendedores.contains(vendedor)){
            throw Exception("YA EXISTE VENDEDOR")
        }
        vendedores.add(vendedor)
    }
    fun vendedorEstrella(): Vendedor? = vendedores.maxBy { it.puntajeCertificaciones() }
    fun puedeCubrir( unaCiudad: Ciudad) = vendedores.any { it.puedeTrabajarEn(ciudad = unaCiudad) }
    fun vendedoresGenericos(): List<Vendedor> = vendedores.filter { it.esGenerico() }
    fun esRobusto() = vendedores.count{it.esFirme()} >= 3
}