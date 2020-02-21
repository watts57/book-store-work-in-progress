package bookstore;

public class Book {


    private String title;
    private String authorLast;
    private String authorFirst;
    private String publisher;
    private String year;
    private String pages;




    public Book(String title, String authorLast, String authorFirst, String publisher, String year, String pages){

            this.title = title;
            this.authorLast = authorLast;
            this.authorFirst = authorFirst;
            this.publisher = publisher;
            this.year = year;
            this.pages = pages;

            
            




    }
    public String header ="\n"+" ________________________________________________________________________________________________________________________________"+"\n";

    @Override
   public String toString(){
        return this.title + ",  by " + authorFirst + " " +authorLast;
    }



    public String getTitle(){
        return this.title;
    }

    public String getAuthorLast(){
        return this.authorLast;
    }
    public String getAuthorFirst(){
        return this.authorFirst;
    }

    public String getPublisher(){
        return this.publisher;
    }
    public String getYear(){
        return this.year;
    }

    public String getPages(){
        return this.pages;
    }



    public void setAuthor(String newFirst,String newLast){ ///
        this.authorFirst = newFirst;
        this.authorLast = newLast;
    }

    public void setPublisher(String newPublisher){

        this.publisher = newPublisher;
    }
    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setPages(String newPages){

        this.pages = newPages;
    }

    public void setYear(String newYear){
        this.year = newYear;
    }

}
