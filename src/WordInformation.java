
public class WordInformation {
    String word;
    LinkedList<WordOccurrence> occList;
    int size;
    
    WordInformation(String word , int row , int pos )
    {
        this.word=word;
        occList=new LinkedList();
        occList.insertOccurrence(new WordOccurrence(row,pos));
        size = 1;
    }
    

    public String toString()
    {
        String str ="";
        Node<WordOccurrence> temp = occList.head;
        while(temp!=null)
        {
            str+=temp.data+", ";
            temp=temp.next;
        }
        return str;
    }
}
