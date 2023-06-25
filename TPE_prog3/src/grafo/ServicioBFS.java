package grafo;
import java.util.*;

public class ServicioBFS {
    private Grafo<?> grafo;
    private ArrayList<Integer> recorrido;
    private HashMap<Integer, Boolean> registro;

    public ServicioBFS(Grafo<?> grafo) {
        this.grafo = grafo;
        recorrido = new ArrayList<>();
        registro = new HashMap<>();
    }

    public List<Integer> bfsForest() {
        Queue<Integer> queue = new LinkedList<>();
        asignarEstado();

        Iterator<Integer> iterador = grafo.obtenerVertices();
        while(iterador.hasNext()){
            int vertice = iterador.next();
            if(registro.get(vertice).equals(false)) {
                registro.replace(vertice, false, true);
                if (!recorrido.contains(vertice)) {
                    recorrido.add(vertice);
                }
                bfs(vertice, queue);
            }
        }
       return recorrido;
    }

    private void bfs(int v, Queue<Integer> queue){
        queue.add(v);

        while(!queue.isEmpty()){
            int vertice = queue.poll();
            Iterator<Integer> iterador = grafo.obtenerAdyacentes(vertice);
            while(iterador.hasNext()){
                int ady = iterador.next();
                if(registro.get(ady).equals(false)){
                    registro.replace(ady, false, true);
                    if(!recorrido.contains(ady)){
                        recorrido.add(ady);
                        queue.offer(ady);
                    }
                }
            }
        }
    }

    private void asignarEstado(){
        Iterator<Integer> iterador = grafo.obtenerVertices();
        while (iterador.hasNext()){
            int vertice = iterador.next();
            registro.put(vertice, false);
        }
    }
}