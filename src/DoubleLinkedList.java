public class DoubleLinkedList implements ListADT {
    private DoubleMyNode first,current,last;
    private int size;
    
    public DoubleLinkedList(){
        first=current=last=null;
        size = 0;
    }

    @Override
    public void insert(Object e) throws Exception {
        DoubleMyNode p = new DoubleMyNode(e);
        size++;
        if(isEmpty()){
            first=current=p;
        }else{
            p.setNextNode(current.getNextNode());
            p.setPreviousNode(current);
            current.setNextNode(p);
            if (p.getNextNode() != null){
                p.getNextNode().setPreviousNode(p);
            }
            last=current=p;
        }
    }

    @Override
    public Object retrieve() throws Exception {
        if(isEmpty()){
            throw new Exception("List is empty");
        }else{
            return current.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        return first==null;
    }

    @Override
    public void delete() throws Exception {
        if (isEmpty()){
            throw new Exception("List is empty");
        }else{
            if(first==current && current.getNextNode()==null){
                first=current=last=null;
            }else if(first==current && current.getNextNode()!=null){
                first=first.getNextNode();
                current=first;
            }else{
                current.getPreviousNode().setNextNode(current.getNextNode());
                current.getNextNode().setPreviousNode(current.getPreviousNode());
                current=first;
            }
            size--;
        }
    }

    @Override
    public void update(Object e) throws Exception {
        if(isEmpty()){
            throw new Exception("List is empty");
        }else{
            current.setData(e);
        }
    }

    @Override
    public void findFirst() throws Exception {
        if(isEmpty()){
            throw new Exception("List is empty");
        }else{
            current = first;}
    }

    @Override
    public void findNext() throws Exception {
        if(isEmpty()){
            throw new Exception("List is empty");
        }else if(current.getNextNode() == null){throw new Exception("Not have a next node");}
        else{current = current.getNextNode();}
    }
    public void findPrevious() throws Exception {
        if(isEmpty()){
            throw new Exception("List is empty");
        }else if(current.getPreviousNode() == null){throw new Exception("Not have a previous node");}
    else{current = current.getPreviousNode();}}


    @Override
    public boolean isFull() {
        return false;
    }
    @Override
    public boolean findKey(Object tKey){
        return  this.equals(tKey);
    }
    public boolean checkAtFirst(){
        return current == first;
    }
    public Object getNext(){
        return current.getNextNode();
    }
    public void findLast(){
        current = last;
    }
    public int getSize(){
        return size;
    }
}
 
