interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

class Book implements LibraryItem {
    private String title;
    private String author;
    private int publicationYear;
    private boolean borrowed;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String toString() {
        return "Book - Title: " + title + ", Author: " + author + ", Publication Year: " + publicationYear;
    }
}

// DVD class implementing LibraryItem
class DVD implements LibraryItem {
    private String title;
    private String director;
    private int runningTime;
    private boolean borrowed;

    public DVD(String title, String director, int runningTime) {
        this.title = title;
        this.director = director;
        this.runningTime = runningTime;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getRunningTime() {
        return runningTime;
    }

    @Override
    public String toString() {
        return "DVD - Title: " + title + ", Director: " + director + ", Running Time: " + runningTime + " minutes";
    }
}

abstract class LibraryUser {
    protected LibraryItem[] borrowedItems;

    public LibraryUser() {
        this.borrowedItems = new LibraryItem[5];
    }

    protected LibraryItem[] getBorrowedItems() {
        return borrowedItems;
    }

    public void borrowItem(LibraryItem item) {
        for (int i = 0; i < borrowedItems.length; i++) {
            if (borrowedItems[i] == null) {
                borrowedItems[i] = item;
                item.borrowItem();
                return;
            }
        }
        System.out.println("User cannot borrow more items.");
    }

    public void returnItem(LibraryItem item) {
        for (int i = 0; i < borrowedItems.length; i++) {
            if (borrowedItems[i] == item) {
                borrowedItems[i] = null;
                item.returnItem();
                return;
            }
        }
        System.out.println("User did not borrow this item.");
    }

    public abstract void printItemsBorrowed();
}

class Student extends LibraryUser {
    @Override
    public void printItemsBorrowed() {
        System.out.println("Borrowed items:");
        for (LibraryItem item : getBorrowedItems()) {
            if (item != null) {
                System.out.println(" - " + item);
            }
        }
        System.out.println("==========================================================================================");
    }
}

class Teacher extends LibraryUser {
    @Override
    public void printItemsBorrowed() {
        System.out.println("Borrowed items:");
        for (LibraryItem item : getBorrowedItems()) {
            if (item != null) {
                System.out.println(" - " + item);
            }
        }
        System.out.println("==========================================================================================");
    }
}

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        DVD dvd1 = new DVD("Inception", "Christopher Nolan", 148);

        Student student1 = new Student();
        Teacher teacher1 = new Teacher();

        student1.borrowItem(book1);
        student1.borrowItem(dvd1);

        teacher1.borrowItem(book1);
        teacher1.borrowItem(dvd1);

        student1.returnItem(book1);
        teacher1.returnItem(dvd1);

        System.out.println("Student: Rafi");
        student1.printItemsBorrowed();

        System.out.println("\nTeacher: Rubio");
        teacher1.printItemsBorrowed();
    }
}


