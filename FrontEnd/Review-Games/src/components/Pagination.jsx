
const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  const goToTop = () => {
    window.scrollTo(0,0);
  }
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
    <button onClick={goToTop}>Go to the Top</button>
      )
    
  );
};

export default Pagination;
