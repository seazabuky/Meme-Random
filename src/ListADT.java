interface ListADT {
    
    public void insert(Object e) throws Exception;

    public Object retrieve() throws Exception;

    public boolean isEmpty();

    public void delete() throws Exception;

    public void update(Object e) throws Exception;

    public void findFirst() throws Exception;

    public void findNext() throws Exception;

    public void findPrevious() throws Exception;

    public boolean checkAtFirst();

    public Object getNext();

    public void findLast();

    public int getSize();
}
