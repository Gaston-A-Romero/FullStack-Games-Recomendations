import { Link, useRouteError } from "react-router-dom";

export default function ErrorPage() {
  const error = useRouteError();
  console.error(error);

  return (
    <div id="error-page" className="
    bg-slate-200 h-full w-full p-2 m-2 flex justify-center items-center flex-col">
      <h2 className="text-[2.5rem]">Oops!</h2>
      <p className="text-[1.5rem]">Sorry, an unexpected error has occurred.</p>
      <p className="text-red-600 underline underline-offset-2">
        <i className="text-[1.5rem]">{error.statusText || error.message}</i>
      </p>
      <Link to="/" className='bg-black text-white p-2 m-2 rounded-lg hover:bg-slate-400'>Go back</Link>
    </div>
  );
}