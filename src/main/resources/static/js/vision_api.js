
const visionAPI = {
  detectText : async function (formObj) {

    console.log("upload to server......")
    console.log(formObj)

    const response = await axios({
      method: 'post',
      url: '/vision/detectText',
      data: formObj,
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });

    return response.data
  },

  // async function removeFileToServer(uuid, fileName) {
  //
  //   const response = await axios.delete(`/remove/${uuid}_${fileName}`)
  //
  //   return response.data
  //
  // }
}
