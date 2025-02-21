import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class LoanDAO {

    public void borrowBook(int userId, int bookId) {
        String query = "INSERT INTO loans (user_id, book_id, loan_date) VALUES (?, ?, NOW())";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int loanId) {
        String query = "UPDATE loans SET return_date = NOW() WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, loanId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Loan> getLoansByUser(int userId) {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM loans WHERE user_id = ? AND return_date IS NULL";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int bookId = rs.getInt("book_id");
                Date loanDate = rs.getDate("loan_date");
                Date returnDate = rs.getDate("return_date");

                loans.add(new Loan(id, userId, bookId, loanDate, returnDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loans;
    }
}
