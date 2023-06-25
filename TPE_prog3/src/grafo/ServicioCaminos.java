package grafo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {
	private Grafo<?> grafo;
    private int origen;
    private int destino;
    private int lim;
    private List<List<Integer>> resultado;
    private List<Arco<?>> arcosVisitados;

    public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
        this.resultado = new ArrayList<>();
        this.arcosVisitados = new ArrayList<>();
    }

    public List<List<Integer>> caminos() {
        ArrayList<Integer> camino = new ArrayList<>();
        camino.add(origen);

        caminos(origen, camino, 0);
        return resultado;
    }

    private void caminos(int origen, ArrayList<Integer> camino, int cantArcos){
        if(origen == destino && cantArcos <= lim){
            resultado.add(new ArrayList<>(camino));
        } else if (cantArcos < lim) {
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(origen);
            while(adyacentes.hasNext()){
                int ady = adyacentes.next();
                Arco<?> arco = grafo.obtenerArco(origen, ady);
                if(!arcosVisitados.contains(arco)){
                    this.arcosVisitados.add(arco);
                    camino.add(ady);
                    caminos(ady, camino, cantArcos + 1);
                    camino.remove(camino.size() - 1);
                    this.arcosVisitados.remove(arco);
                }
            }
        }
    }

}
