import { IncompleteBookError } from 'errors/errors';
import { is_valid_isbn, is_valid_num_copies, is_valid_year } from 'helpers';

class Book {
    constructor(...args) {
        for (var arg, _pj_c = 0, _pj_a = args, _pj_b = _pj_a.length; _pj_c < _pj_b; _pj_c += 1) {
            arg = _pj_a[_pj_c];

            if (arg === "") {
                throw new IncompleteBookError("All fields are required.");
            }
        }

        if (args.length === 1) {
            this.isbn = is_valid_isbn(args[0]["isbn"]);
            this.title = args[0]["title"];
            this.author = args[0]["author"];
            this.year = is_valid_year(args[0]["year"]);
            this.copies = is_valid_num_copies(args[0]["copies"]);
        } else {
            this.isbn = is_valid_isbn(args[0]);
            this.title = args[1];
            this.author = args[2];
            this.year = is_valid_year(args[3]);
            this.copies = is_valid_num_copies(args[4]);
        }
    }
}
