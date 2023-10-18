
const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  const goToTop = () => {
    window.scrollTo(0,0);
  }
  return (
    (totalPages > 0 ?
      <div className="flex justify-center items-center p-2 m-2 gap-3">
      <button
        disabled={currentPage === 0}
        onClick={() => onPageChange(currentPage - 1)}
        className="bg-black text-white p-2 m-2 rounded-lg hover:bg-slate-400"
      >
        Last Page
      </button>
      <button
        disabled={currentPage === totalPages - 1}
        onClick={() => onPageChange(currentPage + 1)}
        className="bg-black text-white p-2 m-2 rounded-lg hover:bg-slate-400"
      >
        Next Page
      </button>
    </div>
    :
    <button onClick={goToTop}
    className="bg-black text-white p-2 m-2 rounded-lg hover:bg-slate-400">Go to the Top</button>
      )
    
  );
};

export default Pagination;
