public class MiListaDoble implements ListInterface{
    private DoubleNode cabeza;
    private DoubleNode cola;
    private int size;

    public MiListaDoble() {
        this.cabeza = null;
        this.cola = null;
        this.size = 0;
    }
    @Override
    public boolean isEmpty() {
        return this.cabeza == null;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void clear() {
        this.cabeza = null;
        this.cola = null;
        this.size = 0;

    }

    @Override
    public Object getHead() {
        if (isEmpty()) {
            return null;
        }
        return this.cabeza.dato;
    }

    @Override
    public Object getTail() {
        if (isEmpty()) {
            return null;
        }
        return this.cola.dato;
    }

    @Override
    public Object get(DoubleNode node) {
        if (node == null) {
            return null;
        }
        return node.dato;
    }

    @Override
    public DoubleNode search(Object object) {
        DoubleNode actual = this.cabeza;
        while (actual != null) {
            if (actual.dato == null && object == null) {
                return actual;
            }
            if (actual.dato != null && actual.dato.equals(object)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public boolean add(Object object) {
        DoubleNode nuevo = new DoubleNode(object);
        if (isEmpty()) {
            this.cabeza = nuevo;
            this.cola = nuevo;
        } else {
            this.cola.siguiente = nuevo;
            nuevo.anterior = this.cola;
            this.cola = nuevo;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean insert(DoubleNode node, Object object) {
        if (node == null) {
            return false;
        }
        if (node == this.cola) {
            return add(object);
        }

        DoubleNode nuevo = new DoubleNode(object);
        DoubleNode siguienteNodo = node.siguiente;

        nuevo.siguiente = siguienteNodo;
        nuevo.anterior = node;
        node.siguiente = nuevo;

        if (siguienteNodo != null) {
            siguienteNodo.anterior = nuevo;
        }

        this.size++;
        return true;
    }

    @Override
    public boolean insert(Object objectRef, Object object) {
        DoubleNode nodoReferencia = search(objectRef);
        if (nodoReferencia == null) {
            return false;
        }
        return insert(nodoReferencia, object);
    }
    @Override
    public boolean insertHead(Object object) {
        DoubleNode nuevo = new DoubleNode(object);
        if (isEmpty()) {
            this.cabeza = nuevo;
            this.cola = nuevo;
        } else {
            nuevo.siguiente = this.cabeza;
            this.cabeza.anterior = nuevo;
            this.cabeza = nuevo;
        }
        this.size++;
        return true;
    }
    @Override
    public boolean insertTail(Object object) {
        return add(object);
    }

    @Override
    public boolean set(DoubleNode node, Object object) {
        if (node == null) {
            return false;
        }
        node.dato = object;
        return true;
    }

    @Override
    public boolean remove(DoubleNode node) {
        if (node == null || isEmpty()) {
            return false;
        }

        if (node == this.cabeza) {
            if (this.cabeza == this.cola) {
                this.cabeza = null;
                this.cola = null;
            } else {
                this.cabeza = this.cabeza.siguiente;
                this.cabeza.anterior = null;
            }
            this.size--;
            return true;
        }

        if (node == this.cola) {
            this.cola = this.cola.anterior;
            this.cola.siguiente = null;
            this.size--;
            return true;
        }

        DoubleNode anteriorNodo = node.anterior;
        DoubleNode siguienteNodo = node.siguiente;

        if (anteriorNodo != null) {
            anteriorNodo.siguiente = siguienteNodo;
        }

        if (siguienteNodo != null) {
            siguienteNodo.anterior = anteriorNodo;
        }

        this.size--;
        return true;
    }

    @Override
    public boolean contains(Object object) {
        return search(object) != null;
    }
    @Override
    public Object[] toArray() {
        Object[] arreglo = new Object[this.size];
        DoubleNode actual = this.cabeza;
        int i = 0;

        while (actual != null) {
            arreglo[i] = actual.dato;
            actual = actual.siguiente;
            i++;
        }

        return arreglo;
    }

    @Override
    public Object[] toArray(Object[] object) {
        if (object == null) {
            return new Object[0];
        }

        if (object.length < this.size) {
            object = (Object[]) java.lang.reflect.Array.newInstance(
                    object.getClass().getComponentType(),
                    this.size
            );
        }

        DoubleNode actual = this.cabeza;
        int i = 0;
        while (actual != null) {
            object[i] = actual.dato;
            actual = actual.siguiente;
            i++;
        }

        if (object.length > this.size) {
            object[this.size] = null;
        }

        return object;
    }
    @Override
    public MiListaDoble subList(DoubleNode from, DoubleNode to) {
        if (from == null || to == null) {
            return null;
        }

        MiListaDoble subLista = new MiListaDoble();
        DoubleNode actual = from;
        boolean encontrado = false;

        while (actual != null) {
            subLista.add(actual.dato);
            if (actual == to) {
                encontrado = true;
                break;
            }
            actual = actual.siguiente;
        }

        if (!encontrado) {
            return null;
        }

        return subLista;
    }

    @Override
    public MiListaDoble sortList() {
        if (isEmpty()) {
            return new MiListaDoble();
        }

        Object[] arreglo = toArray();
        java.util.Arrays.sort(arreglo);

        MiListaDoble listaOrdenada = new MiListaDoble();
        for (Object elemento : arreglo) {
            listaOrdenada.add(elemento);
        }

        return listaOrdenada;
    }
}
