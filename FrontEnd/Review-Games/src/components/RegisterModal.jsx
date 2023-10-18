import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { Register } from '../services/LoginService';
import { Navigate } from 'react-router-dom';

export default function FormDialog() {
  const [open, setOpen] = React.useState(false);
  const [registered, setRegistered] = React.useState(false);
  const [email, setEmail] = React.useState('');
  const [password, setPassword] = React.useState('');
  const [validationErrorEmail, setValidationErrorEmail] = React.useState('');
  const [validationErrorPassword, setValidationErrorPassword] = React.useState('');
  const [showPassword, setShowPassword] = React.useState(false);

  const handleTogglePassword = () => {
    setShowPassword(!showPassword);
  };
  const handleClickOpen = () => {
    setOpen(true);
  };

  const validateEmail = (data) => {
    return /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/.test(data);
  };

  const validatePassword = (data) => {
    return /^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*[0-9]).{8,}$/.test(data);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleRegistration = () => {
    if (validateEmail(email)) {
      setValidationErrorEmail('');
      if (validatePassword(password)) {
        setValidationErrorPassword('');
        Register(email, password);
        setRegistered(true);
        setOpen(false);
      } else {
        setValidationErrorPassword('Invalid password. Please check the requirements.');
      }
    } else {
      setValidationErrorEmail('Invalid email. Please check the requirements.');
    }
  };

  React.useEffect(() => {
    if (email) {
      if (validateEmail(email)) {
        setValidationErrorEmail('');
      } else {
        setValidationErrorEmail('Invalid email. Please check the requirements.');
      }
    }
    if (password) {
      if (validatePassword(password)) {
        setValidationErrorPassword('');
      } else {
        setValidationErrorPassword('Invalid password. Please check the requirements.');
      }
    }
  }, [email, password]);

  return (
    !registered ? 
      <div className='p-2 m-2'>
        <button
          variant="outlined"
          onClick={handleClickOpen}
          className='bg-black text-white p-2 m-2 rounded-lg hover:bg-slate-400'
        >
          Register
        </button>
        <Dialog open={open} onClose={handleClose}>
          <DialogTitle >Sign Up</DialogTitle>
          <DialogContent>
            <DialogContentText>
              To register, we will need an email and a password. We will send an email to confirm your registration.
            </DialogContentText>
            <DialogContentText className='font-semibold'>
                <strong>Requirements:<br/>
                *Email should be like: name@domain.com<br/>
                *Password should have:<br/>
                 One uppercase letter, one special character, one digit and a minimum of 8 characters.</strong>
            </DialogContentText>
            <TextField
              onChange={handleEmailChange}
              autoFocus
              margin="dense"
              id="email"
              label="Email Address"
              type="email"
              fullWidth
              variant="standard"
              required
            />
            {validationErrorEmail && <p style={{ color: 'red' }}>{validationErrorEmail}</p>}
            <TextField
              onChange={handlePasswordChange}
              autoFocus
              margin='dense'
              id='password'
              label='Password'
              type={showPassword ? 'text' : 'password'}
              fullWidth
              variant='standard'
              required
            />
            <button onClick={handleTogglePassword} className='bg-black p-1 m-1 hover:bg-slate-400 text-white border-r-8' >
                {showPassword ? 'Hide Password' : 'Show Password'}
            </button>
            
            {validationErrorPassword && <p style={{ color: 'red' }}>{validationErrorPassword}</p>}
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose}>Cancel</Button>
            <Button onClick={handleRegistration}>Register</Button>
          </DialogActions>
        </Dialog>
      </div>
    : 
      <Navigate to="/activate-account" replace={true} />
    
  )
}
