
const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  return (
    <div>
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
  );
};

export default Pagination;
