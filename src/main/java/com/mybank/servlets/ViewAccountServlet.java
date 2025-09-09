@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String sid = request.getParameter("accountId");

    try {
        if (sid == null || sid.isBlank()) {
            request.getRequestDispatcher("/accountDetails.jsp").forward(request, response);
            return;
        }

        int accountId = Integer.parseInt(sid);
        Account account = accountService.getAccount(accountId);
        List<Transaction> transactions = accountService.getTransactionHistory(accountId);

        request.setAttribute("account", account);
        request.setAttribute("transactions", transactions);

        request.getRequestDispatcher("/accountDetails.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", e.getMessage());
        request.getRequestDispatcher("/accountDetails.jsp").forward(request, response);
    }
}
