import useGlobalState from "../store/store";
import { useState } from "react";
import { Button,TextField,Dialog,DialogActions,DialogContent,DialogContentText,DialogTitle } from '@mui/material';

function EditProfileModal(){
    const [isModalOpen, setIsModalOpen] = useState(true);
    const profile = useGlobalState((state) => state.profile);

    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };
    const saveProfile = (updatedProfile) => {
        return true
    };

    return (
        <div>
            <Button onClick={openModal}>Inicializa tu Perfil</Button>
            <Dialog open={openModal} onClose={closeModal}>
                <DialogTitle>Change Profile Info</DialogTitle>
                <DialogContent>
                    <DialogContentText>Username</DialogContentText>
                    <TextField></TextField>

                </DialogContent>
                <DialogActions>
                    <Button onClick={saveProfile}>Confirm</Button>
                    <Button onClick={closeModal}>Cancel</Button>
                </DialogActions>


            </Dialog>
            
        
            
        </div>
        
    )
}
export default EditProfileModal;