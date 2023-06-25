package grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioDFS {
    private Grafo<?> grafo;
    private HashMap<Integer, String> registro;
    private ArrayList<Integer> lista;
    public ServicioDFS(Grafo<?> grafo) {
        this.grafo = grafo;
        this.registro = new HashMap<>();
        this.lista = new ArrayList<>();
    }

    public List<Integer> dfsForest() {
        this.asignarColor();
        Iterator<Integer> vertices = grafo.obtenerVertices();
        while(vertices.hasNext()) {
            Integer v = vertices.next();
            if(registro.get(v).equals("blanco")) {
                dfsVisit(v);
            }
        }
        return lista;
    }

    private void dfsVisit(Integer v) {
        registro.put(v, "amarillo");
        lista.add(v);
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(v);
        while(adyacentes.hasNext()) {
            Integer a = adyacentes.next();
            if(registro.get(a).equals("blanco")) {
                dfsVisit(a);
            }
        }
        
        registro.put(v, "negro");
    }

    private void asignarColor() {
        Iterator<Integer> vertices = grafo.obtenerVertices();
        while(vertices.hasNext()) {
            Integer v = vertices.next();
            registro.put(v, "blanco");
        }
    }
}
