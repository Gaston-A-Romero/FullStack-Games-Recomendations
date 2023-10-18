import { useEffect } from 'react';
import { useParams } from 'react-router-dom';

export function AccountActivationPage(props){
    const {code} = useParams()
    console.log(props.email); 

    useEffect(() => {
        console.log(code)
        
      }, [code]);
  return (
    <div className='flex items-center justify-center font-bold h-10 bg-slate-200'>
      <h3>An Email has been sent to your email, please check it out</h3>


    </div>
  );
};

export default AccountActivationPage;
