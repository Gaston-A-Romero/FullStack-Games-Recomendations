const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  return (
    (totalPages > 0 ?
      <div className="pagination-container">
      <button
        disabled={currentPage === 0}
        onClick={() => onPageChange(currentPage - 1)}
      >
        Last Page
      </button>
      <button
        disabled={currentPage === totalPages - 1}
        onClick={() => onPageChange(currentPage + 1)}
      >
        Next Page
      </button>
    </div>
    :
    <span>Theres only one page</span>
      )
    
  );
};

export default Pagination;
