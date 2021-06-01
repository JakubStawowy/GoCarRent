import React, {useEffect, useState} from 'react';
import '../components/components.css';
import {
    Button, Card,
    Container, Fab,
    Grid,
    makeStyles,
    MenuItem,
    Select,
    TextField,
    Typography
} from "@material-ui/core";
import PublishIcon from '@material-ui/icons/Publish';
import RoomIcon from '@material-ui/icons/Room';
import carBrands from "../data/carBrands";
import ImageIcon from '@material-ui/icons/Image';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';
import {useDispatch} from "react-redux";
import {
    addAnnouncement,
    editAnnouncement,
    deleteAnnouncement,
    getAnnouncement
} from "../actions/actionRepository";
import {useHistory} from "react-router";

const useStyles = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexDirection: 'column',
        height: '100%',
        justifyContent: 'space-around'
    },
    form: {
        height: '70%',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
    },
    item: {
        width: '90%'
    },
    gridContainer: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '80%',
        borderRadius: '2em'
    },
    button: {
        background: '#4BBEBAE0',
    },
    deleteButton: {
        background: '#FA8072'
    },
    titleField: {
        marginTop: '2em'
    },
    confirmArea: {
        borderRadius: '2em',
        background: '#FA8072',
        display: 'flex',
        justifyContent: 'center',
        // alignItems: 'center'
    },
    confirmField: {
        background: '#FCA294',
        width: '40%',
    }
}));

export default function AnnouncementForm(props) {
    const classes = useStyles();
    const [title, setTitle] = useState('');
    const [price, setPrice] = useState('');
    const [timeUnit, setTimeUnit] = useState('');
    const [brand, setBrand] = useState('');
    const [model, setModel] = useState('');
    const [deleteStatus, setDeleteStatus] = useState(false);
    const [password, setPassword] = useState('');

    const dispatch = useDispatch();
    const history = useHistory();

    const handleSuccess = (message, endpoint) => {
        alert(message);
        history.replace(endpoint);
    }

    useEffect(() => {
        if (props.edit) {
            getAnnouncement(props.announcementId).then((response) => {
                setTitle(response.data.title);
                setPrice(response.data.amount);
                setTimeUnit(response.data.timeUnit);
                setBrand(response.data.carBrand);
                setModel(response.data.carModel);
            })
        }
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();

        const data = {
            "title": title,
            "amount": price,
            "currency": "PLN",
            "timeUnit": timeUnit,
            "carBrand": brand,
            "carModel": model,
            "authorId": localStorage.getItem("userId")
        };

        props.edit ?
            dispatch(editAnnouncement(data, props.announcementId)).then(
                () => handleSuccess('Announcement edited successfully', "/")
            ).catch(
                (error) => alert(error)
            )
            :
            dispatch(addAnnouncement(data)).then(
                () => handleSuccess('Announcement added successfully', "/")
            ).catch(
                (error) => alert(error)
            );
    }

    const handleDelete = () => {
        setDeleteStatus(true);
    }

    const handleConfirmedDelete = () => {
        dispatch(deleteAnnouncement({
            "announcementId": props.announcementId,
            "password": password
        })).then(
            () => handleSuccess('Announcement deleted successfully', "/")
        ).catch(
            (error) => alert(error)
        );
    }

    const handlePasswordChange = (e) => {
        setPassword(e);
    }

    return (
        <Container className={classes.container}>
            <Typography variant={'h4'} align={'center'}>
                {props.edit ? "Edit announcement" : "Add announcement"}
            </Typography>
            <form className={classes.form} onSubmit={handleSubmit}>
                <Grid container justify={'center'} className={classes.gridContainer}>
                    <Grid item xs={10} className={classes.titleField}>
                        <TextField
                            type={'text'}
                            label={'Title'}
                            className={classes.item}
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={5}>
                        <TextField
                            type={'text'}
                            label={'Price'}
                            className={classes.item}
                            value={price}
                            onChange={(e) => setPrice(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            className={classes.item}
                            value={timeUnit}
                            onChange={(e) => setTimeUnit(e.target.value)}
                        >
                            <MenuItem value={"HOURS"}>
                                per hour
                            </MenuItem>
                            <MenuItem value={"DAYS"}>
                                per day
                            </MenuItem>
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            className={classes.item}
                            value={brand}
                            onChange={(e) => setBrand(e.target.value)}
                        >
                            {
                                carBrands.map(brand => {
                                    return (
                                        <MenuItem selected={false} value={brand.brand}>{brand.brand}</MenuItem>
                                    );
                                })
                            }
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            className={classes.item}
                            value={model}
                            onChange={(e) => setModel(e.target.value)}
                        >
                            {
                                carBrands[0].models.map(model => {
                                    return (
                                        <MenuItem value={model}>{model}</MenuItem>
                                    );
                                })
                            }
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Button className={classes.item}>
                            Localisation
                            <RoomIcon />
                        </Button>
                    </Grid>
                    <Grid item xs={5}>
                        <Button className={classes.item}>
                            Image
                            <ImageIcon />
                        </Button>
                    </Grid>
                </Grid>
                <Fab variant={'extended'} className={classes.button} type={"submit"}>
                    {props.edit ? "Save": "Publish"}
                    <PublishIcon />
                </Fab>
            </form>

            {(props.edit && !deleteStatus) &&
            <Fab variant={'extended'} className={classes.deleteButton} onClick={handleDelete}>
                Delete
                <DeleteForeverIcon/>
            </Fab>
                }
            {
                deleteStatus &&
                <Card className={classes.confirmArea}>
                    <TextField
                        label={'password'}
                        type={'password'}
                        value={password}
                        onChange={(e) => handlePasswordChange(e.target.value)}
                    />
                    <Fab variant={"extended"} className={classes.confirmField} onClick={handleConfirmedDelete}>
                        Confirm
                        <DeleteForeverIcon />
                    </Fab>
                </Card>
            }
        </Container>
    );
}