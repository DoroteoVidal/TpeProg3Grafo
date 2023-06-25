package grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, ArrayList<Arco<T>>> grafo;
    private int cantArcos;

    public GrafoDirigido() {
        this.grafo = new HashMap<>();
        this.cantArcos = 0;
    }

    /**
     * Complejidad: O(1) debido a que la insercion al hashmap tiene una complejidad constante de 1.
     */
    @Override
    public void agregarVertice(int verticeId) {
    	if(grafo.get(verticeId) == null) {
   		 	grafo.put(verticeId, new ArrayList<>());
    	}
    }

    /**
     * Complejidad: O(n) debido a que debera recorrer todos los arcos del verticeId1
     * para verificar si el arco no existe para agregarlo.
     */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(!existeArco(verticeId1, verticeId2)) {
            Arco<T> aux = new Arco<>(verticeId1, verticeId2, etiqueta);
            grafo.get(verticeId1).add(aux);
            cantArcos++;
        }
    }

    /**
     * Complejidad: O(n*a) debido a que se recorreran todos los vertices y a su vez,
     * se invoca al metodo borrarArco, que recorrera los arcos de cada vertice en
     * busca de un arco en el que el vertice destino sea el vertice a borrar.
     */
    @Override
    public void borrarVertice(int verticeId) {
        if(contieneVertice(verticeId)){            
            for(Integer e: grafo.keySet()){
            	borrarArco(verticeId, e);
                borrarArco(e, verticeId);
            }
            grafo.remove(verticeId);
        }
    }

    /**
     * Complejidad: O(n) debido a que el metodo removeIf recorre todos arcos y borra aquel que cumple con la condicion.
     */
    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        ArrayList<Arco<T>> aux = grafo.get(verticeId1);
        if (aux != null) {
            if(aux.removeIf(arco -> arco.getVerticeDestino() == verticeId2)) {
            	cantArcos--;
            }   
        } 
    }

    /**
     * Complejidad: O(1) debido a que el metodo get de Hashmap posee una complejidad constante de 1.
     */
    @Override
    public boolean contieneVertice(int verticeId) {
        return grafo.get(verticeId) != null;
    }

    /**
     * Complejidad: O(n) donde n es la cantidad de arcos que tiene vertice1, debido a que debe
     * recorrer cada arco para verificar si el vertice destino de un arco es vertice2.
     */
    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        if (contieneVertice(verticeId1) && contieneVertice(verticeId2)) {
            ArrayList<Arco<T>> aux = grafo.get(verticeId1);
            if (aux != null) {
                for (Arco<T> a : aux) {
                    if (a.getVerticeDestino() == verticeId2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Complejidad: O(n) donde n es la cantidad de arcos de vertice1, debido a que debe
     * recorrer cada arco y verificar si existe un arco con destino vertice2 para retornarlo.
     */
    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        if(existeArco(verticeId1, verticeId2)){
            ArrayList<Arco<T>> aux = grafo.get(verticeId1);
            for (Arco<T> a: aux){
                if(a.getVerticeDestino() == verticeId2){
                    return a;
                }
            }
        }
        return null;
    }

    /**
     * Complejidad: O(1) debido a que pide el size al Hashmap
     * y este tiene una complejidad constante de 1.
     */
    @Override
    public int cantidadVertices() {
        return grafo.size();
    }

    /**
     * Complejidad: O(1) ya que la clase contiene una variable que lleva el conteo de arcos
     * cada vez que se agrega o elimina uno.
     */
    @Override
    public int cantidadArcos() {
       return cantArcos;
    }

    /**
     * Complejidad: O(1) debido a que solo se llama al iterador.
     */
    @Override
    public Iterator<Integer> obtenerVertices() {
        return grafo.keySet().iterator();
    }


    /**
     * Complejidad: O(n) debido a que se recorreran todos los arcos del verticeId
     *  en busca de los vertices destino de cada arco.
     */
    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        ArrayList<Arco<T>> arcos = grafo.get(verticeId);
        ArrayList<Integer> adyacentes = new ArrayList<>();

        if(arcos != null) {
            for (Arco<T> a : arcos) {
                adyacentes.add(a.getVerticeDestino());
            }
            return adyacentes.iterator();
        }
        return null;
    }


    /**
     * Complejidad: O(n*a) debido a que se recorreran todos los vertices y
     * por cada vertice se agregara todos los arcos que tiene.
     */
    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> arcos = new ArrayList<>();

        for(Integer vertice : grafo.keySet()){
            arcos.addAll(grafo.get(vertice));
        }

        return arcos.iterator();
    }

    /**
     * Complejidad: O(1) debido a que solo se llama al iterador y el metodo get tambien tiene
     * una complejidad constante de 1.
     */
    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        return grafo.get(verticeId).iterator();
    }

}
