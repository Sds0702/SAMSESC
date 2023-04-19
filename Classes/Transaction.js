class Transaction
{
    constructor(...args)
    {
        if (args.length === 1)
        {
            this.id = args[0]["id"];
            this.student_id = args[0]["student_id"];
            this.book_isbn = args[0]["book_isbn"];
            this.date_borrowed = args[0]["date_borrowed"];
            this.date_returned = args[0]["date_returned"];
        }
        else
        {
            this.id = args[0];
            this.student_id = args[1];
            this.book_isbn = args[2];
            this.date_borrowed = args[3];
            this.date_returned = args[4];
        }
        this.details = { "id": this.id, "student_id": this.student_id, "book_isbn": this.book_isbn, "date_borrowed": this.date_borrowed, "date_returned": this.date_returned };
    }
}
