package ar.edu.unahur.obj2.vendedores

class CentroDeDistribucion(ciudad: Ciudad,private var vendedores: MutableList<Vendedor>) {
    fun agregarVendedores(vendedor: Vendedor) {
        if(vendedores.contains(vendedor)){
            throw Exception("YA EXISTE VENDEDOR")
        }
        else{
            vendedores.add(vendedor)
        }
    }
    fun vendedorEstrella(): Vendedor? {
        return vendedores.maxBy { it.puntajeCertificaciones() }
    }
    fun puedeCubrir( unaCiudad: Ciudad) = vendedores.any { it.puedeTrabajarEn(ciudad = unaCiudad) }
    fun vendedoresGenericos(): List<Vendedor> {
        return vendedores.filter { it.esGenerico() }
    }
    fun esRobusto() = (vendedores.filter{it.esFirme()}).size >= 3
}