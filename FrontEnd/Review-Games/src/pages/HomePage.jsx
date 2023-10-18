import GamesShowAnimation from "../components/GamesShowAnimation";
import RegisterModal from "../components/RegisterModal"
export default function HomePage(){
    const notMobile = false;
    return (
        <article className="flex justify-center items-center flex-col bg-slate-200">
            <section className="flex justify-center items-center flex-col">
                <h1 className="text-3xl w-full text-center my-2 py-4 ">Welcome to MyTaste</h1>
                <p className="max-w-2xl p-2 m-2 ">Welcome to our gaming review heaven! Join us as we dive into the world of video games, offering candid opinions and expert insights on the latest titles. No matter your gaming preference, we've got you covered. Let's embark on this exciting journey together! </p>

            </section>
            
            {!notMobile ? <GamesShowAnimation/> : null} 
            <section className="flex justify-center items-center flex-col">
                <h6 className="w-full text-lg">Join our community!!!</h6>
                <RegisterModal/>
            </section>

        </article>
    )
}
