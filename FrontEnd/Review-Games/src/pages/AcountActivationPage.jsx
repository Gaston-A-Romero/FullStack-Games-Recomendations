function AcountActivationPage(){
    const checkActivationCode = () => {
        return true;
    }
    return (
        <div>
            <h5>A code has been sended to your email</h5>
            <label htmlFor="activation_code">Enter your code</label>
            <input type="text" id="activation_code" />
            <button type="submit" onClick={checkActivationCode}>Send Code</button>
        </div>
    )
}

export default AcountActivationPage;