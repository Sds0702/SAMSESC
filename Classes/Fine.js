import { timedelta, date } from 'datetime';
import { days_before } from 'helpers';

class Fine
{
    constructor (borrowed, student_id)
    {
        this.days_borrowed = days_before(borrowed, date.today());
        this.amount = this.calculate_fine(borrowed);
        this.due = (date.today() + timedelta({"days": MAX_PAYMENT_DURATION})).strftime("%Y-%m-%d");
        this.type = "LIBRARY_FINE";
        this.student_id = {"studentId": student_id.toString()};
        this.details = {"amount": this.amount, "dueDate": this.due, "type": this.type, "account": this.student_id};
        this.calculate_fine = staticmethod(calculate_fine);
    }
    calculate_fine(borrowed)
    {
        let days_late = days_before(borrowed + timedelta(this.days = MAX_BORROWING_DURATION), date.today());
        if (days_late >= 1)
        {
            return FINE_PER_DAY * days_late;
        } else
        {
            throw ValueError('No fine necessary.');
        }
    }
}

