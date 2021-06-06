import React, {useEffect, useState} from 'react';
import {
    Button, Card,
    Container, Fab,
    Grid,
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
import {useAnnouncementFormStyles} from "../style/AnnouncementFormStyles";

export default function AnnouncementForm(props) {
    const classes = useAnnouncementFormStyles();
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
        if (title !== '' && price !== '' && timeUnit !== '' && brand !== '' && model !== '') {
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
                    () => handleSuccess('AnnouncementListItem edited successfully', "/")
                ).catch(
                    (error) => alert(error)
                )
                :
                dispatch(addAnnouncement(data)).then(
                    () => handleSuccess('AnnouncementListItem added successfully', "/")
                ).catch(
                    (error) => alert(error)
                );
        }
        else alert("Incorrect announcement data");
    }

    const handleDelete = () => {
        setDeleteStatus(true);
    }

    const handleConfirmedDelete = () => {
        dispatch(deleteAnnouncement({
            "announcementId": props.announcementId,
            "password": password
        })).then(
            () => handleSuccess('AnnouncementListItem deleted successfully', "/")
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
                                carBrands.find(elem=>elem.brand===brand) !== undefined &&
                                carBrands.find(elem=>elem.brand===brand).models.map(model => {
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

            {/*{(props.edit && !deleteStatus) &&*/}
            {/*<Fab variant={'extended'} className={classes.deleteButton} onClick={handleDelete}>*/}
            {/*    Delete*/}
            {/*    <DeleteForeverIcon/>*/}
            {/*</Fab>*/}
                }
            {/*{*/}
            {/*    deleteStatus &&*/}
            {/*    <Card className={classes.confirmArea}>*/}
            {/*        <TextField*/}
            {/*            label={'password'}*/}
            {/*            type={'password'}*/}
            {/*            value={password}*/}
            {/*            onChange={(e) => handlePasswordChange(e.target.value)}*/}
            {/*        />*/}
            {/*        <Fab variant={"extended"} className={classes.confirmField} onClick={handleConfirmedDelete}>*/}
            {/*            Confirm*/}
            {/*            <DeleteForeverIcon />*/}
            {/*        </Fab>*/}
            {/*    </Card>*/}
            {/*}*/}
        </Container>
    );
}